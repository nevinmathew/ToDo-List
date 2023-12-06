package com.todo.app.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.todo.app.dto.ToDoDto;
import com.todo.app.entity.Tasks;
import com.todo.app.entity.enums.Priority;
import com.todo.app.entity.enums.Status;
import com.todo.app.repository.CategoryRepository;
import com.todo.app.repository.TaskRepository;
import com.todo.app.repository.projections.ToDoProjection;
import com.todo.app.service.IToDoService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * The `ToDoService` class implements the business logic for managing ToDo
 * tasks. It provides methods for creating, updating, retrieving, and deleting
 * tasks. Asynchronous processing is utilized to improve responsiveness and
 * performance by offloading tasks to a separate thread pool. This service class
 * also includes caching for optimizing task retrieval.
 */
@OpenAPIDefinition(tags = { @Tag(name = "ToDo Service", description = "Operations related to ToDo management") })
@CacheConfig(cacheNames = { "ToDo" })
@Slf4j
@Service
public class ToDoService implements IToDoService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private ThreadPoolTaskExecutor executor;

//	@Autowired
//	EntityManager entityManager;

	/**
	 * Get a list of ToDos.
	 *
	 * @return A list of ToDoProjection objects.
	 */
	@Operation(summary = "Get a list of ToDos")
	@Override
	public List<?> showToDos() {

		try {
			return CompletableFuture.supplyAsync(() -> {
				List<ToDoProjection> tasks = taskRepository.findAllTasks();

				return tasks.isEmpty() ? Collections.emptyList() : tasks;
			}, executor).exceptionally(ex -> {
				ex.printStackTrace();
				throw new RuntimeException("Error while fetching ToDos.", ex);
			}).join();
		} catch (Exception e) {
			LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
			loggingSystem.setLogLevel(getClass().getName(), LogLevel.ERROR);

			log.error("An error occurred while showing ToDo: {}", e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Get a ToDo by ID.
	 *
	 * @param id The ID of the ToDo to retrieve.
	 * @return A ToDoProjection object if found, otherwise null.
	 */
	@Override
	@Cacheable(key = "'getToDo_' + #id", condition = "#id > 0")
	@Operation(summary = "Get a ToDo by ID")
	public ToDoProjection getToDo(int id) {

		try {
			return CompletableFuture.supplyAsync(() -> {
				Optional<ToDoProjection> task = taskRepository.findTodoById(id);

				return task.isPresent() ? task.get() : null;
			}).join();
		} catch (Exception e) {
			LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
			loggingSystem.setLogLevel(getClass().getName(), LogLevel.ERROR);

			log.error("An error occurred while getting ToDo: {}", e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Creates a new ToDo task based on the provided ToDoDto object.
	 *
	 * @param toDo The ToDoDto object representing the task to be created.
	 * @return A message indicating the result of the creation operation.
	 */
	@Override
	@Transactional
	@Operation(summary = "Create a new ToDo")
	public String createToDo(ToDoDto toDo) {

		try {
			CompletableFuture.runAsync(() -> {

				if (taskRepository.existsByTaskName(toDo.getTaskName())) {
					throw new IllegalArgumentException("A task with the same name already exists.");
				}

				Tasks task = new Tasks(toDo.getTaskName(), toDo.getCreatedTimestamp());

				task.setDescription(toDo.getDescription());

				if (toDo.getPriority() != null && !toDo.getPriority().isEmpty()) {
				    try {
				        Priority priority = Priority.valueOf(toDo.getPriority().toUpperCase());
				        task.setPriority(priority.name());
				    } catch (IllegalArgumentException e) {
				        throw new IllegalArgumentException("Invalid priority value: " + toDo.getPriority());
				    }
				}

				if (toDo.getStatus() != null && !toDo.getStatus().isEmpty()) {
				    try {
				        Status status = Status.valueOf(toDo.getStatus().toUpperCase());
				        task.setStatus(status.name());
				    } catch (IllegalArgumentException e) {
				        throw new IllegalArgumentException("Invalid status value: " + toDo.getStatus());
				    }
				}

				if (task.getCategory().getName().equalsIgnoreCase("reminder") && task.getTargetTimestamp() != null)
					task.setTargetTimestamp(toDo.getTargetTimestamp());

				// task.setCategory(entityManager.getReference(Category.class,
				// toDo.getCategoryId()));
				task.setCategory(categoryRepository.findById(toDo.getCategoryId()).get());

				taskRepository.save(task);
			}).join();

			return "The toDo has been created.";
		} catch (Exception e) {
			LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
			loggingSystem.setLogLevel(getClass().getName(), LogLevel.ERROR);

			log.error("An error occurred while creating ToDo: {}", e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Updates an existing ToDo task by its ID based on the provided ToDoDto object.
	 *
	 * @param id   The ID of the ToDo task to update.
	 * @param toDo The ToDoDto object with updated task information.
	 * @return The updated ToDoDto object.
	 */
	@Override
	@Transactional
	@CachePut(key = "'getToDo_' + #id")
	@Operation(summary = "Update a ToDo by ID")
	public ToDoDto updateToDo(int id, ToDoDto toDo) {

		try {
			CompletableFuture.runAsync(() -> {
				Optional<Tasks> task = taskRepository.findById(id);
				if (!task.isPresent())
					throw new EmptyResultDataAccessException("Task with ID " + id + " not found.", id);

				if (!task.get().getTaskName().equals(toDo.getTaskName())
						&& taskRepository.existsByTaskNameAndIdNot(toDo.getTaskName(), id)) {
					throw new IllegalArgumentException("A task with the same name already exists.");
				}

				task.get().setDescription(toDo.getDescription());

				if (toDo.getPriority() != null && !toDo.getPriority().isEmpty()) {
				    try {
				        Priority priority = Priority.valueOf(toDo.getPriority().toUpperCase());
				        task.get().setPriority(priority.name());
				    } catch (IllegalArgumentException e) {
				        throw new IllegalArgumentException("Invalid priority value: " + toDo.getPriority());
				    }
				}

				if (toDo.getStatus() != null && !toDo.getStatus().isEmpty()) {
				    try {
				        Status status = Status.valueOf(toDo.getStatus().toUpperCase());
				        task.get().setStatus(status.name());
				    } catch (IllegalArgumentException e) {
				        throw new IllegalArgumentException("Invalid status value: " + toDo.getStatus());
				    }
				}

				if (task.get().getCategory().getName().equalsIgnoreCase("reminder")
						&& task.get().getTargetTimestamp() != null)
					task.get().setTargetTimestamp(toDo.getTargetTimestamp());

				if (task.isPresent()) {
					task.get().setUpdatedTimestamp(LocalDateTime.now());
					toDo.setUpdatedTimestamp(task.get().getUpdatedTimestamp());
				}

				taskRepository.save(task.get());
			}).join();

			return toDo;
		} catch (Exception e) {
			LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
			loggingSystem.setLogLevel(getClass().getName(), LogLevel.ERROR);

			log.error("An error occurred while updating ToDo: {}", e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Deletes a ToDo task by its ID.
	 *
	 * @param id The ID of the ToDo task to delete.
	 * @return A message indicating the result of the deletion operation.
	 * @throws EmptyResultDataAccessException If the task with the specified ID is
	 *                                        not found.
	 */
	@Override
	@Transactional
	@CacheEvict(key = "'getToDo_' + #id")
	@Operation(summary = "Delete a ToDo by ID")
	public String deleteToDo(int id) {

		try {
			return CompletableFuture.runAsync(() -> {
				Optional<Tasks> task = taskRepository.findById(id);

				if (!task.isPresent())
					throw new EmptyResultDataAccessException("Task with ID " + id + " not found.", id);

				taskRepository.deleteById(id);

			}).thenApply(result -> "The user has been deleted").join();
		} catch (Exception e) {
			LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
			loggingSystem.setLogLevel(getClass().getName(), LogLevel.ERROR);

			log.error("An error occurred while deleting ToDo: {}", e.getMessage(), e);

			throw e;
		}
	}

}
