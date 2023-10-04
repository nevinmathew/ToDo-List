package com.todo.app.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

@OpenAPIDefinition(tags = {@Tag(name = "ToDo Service", description = "Operations related to ToDo management")})
@Service
public class ToDoService implements IToDoService{

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
//	@Autowired
//	EntityManager entityManager;
	
	/**
	 * Get a list of ToDos.
	 *
	 * @return A list of ToDoProjection objects.
	 */
	@Operation(summary = "Get a list of ToDos")
	@Override
	public List<ToDoProjection> showToDos() {
		List<ToDoProjection> tasks = taskRepository.findAllTasks();
		
		return tasks.isEmpty() ? Collections.emptyList() : tasks;
	}

	/**
	 * Get a ToDo by ID.
	 *
	 * @param id The ID of the ToDo to retrieve.
	 * @return A ToDoProjection object if found, otherwise null.
	 */
	@Override
	@Operation(summary = "Get a ToDo by ID")
	public ToDoProjection getToDo(int id) {
		Optional<ToDoProjection> task = taskRepository.findTodoById(id);
		
		return task.isPresent() ? task.get() : null;
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
		
        if (taskRepository.existsByTaskName(toDo.getTaskName())) {
            throw new IllegalArgumentException("A task with the same name already exists.");
        }
        
		Tasks task = new Tasks();
		task.setTaskName(toDo.getTaskName());
		task.setDescription(toDo.getDescription());
		
		if(toDo.getPriority()!=null && !toDo.getPriority().isEmpty())
			task.setPriority(Priority.valueOf(toDo.getPriority().toUpperCase()).name());
		
		if(toDo.getStatus()!=null && !toDo.getStatus().isEmpty())
			task.setStatus(Status.valueOf(toDo.getStatus().toUpperCase()).name());
		
		task.setCreatedTimestamp(LocalDateTime.now());
		
		if(task.getTargetTimestamp()!=null)
			task.setTargetTimestamp(toDo.getTargetTimestamp());
		
//		task.setCategory(entityManager.getReference(Category.class, toDo.getCategoryId()));
		task.setCategory(categoryRepository.findById(toDo.getCategoryId()).get());
		
		taskRepository.save(task);
		return "The toDo has been created.";
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
	@Operation(summary = "Update a ToDo by ID")
	public ToDoDto updateToDo(int id, ToDoDto toDo) { 
        
		Optional<Tasks> task = taskRepository.findById(id);
		if(!task.isPresent())
			throw new EmptyResultDataAccessException("Task with ID "+ id +" not found.", id);
		
        if (!task.get().getTaskName().equals(toDo.getTaskName()) && taskRepository.existsByTaskNameAndIdNot(toDo.getTaskName(), id)) {
            throw new IllegalArgumentException("A task with the same name already exists.");
        }
		
		task.get().setTaskName(toDo.getTaskName()); 
		task.get().setDescription(toDo.getDescription()); 
		
		if(toDo.getPriority()!=null && !toDo.getPriority().isEmpty())
			task.get().setPriority(Priority.valueOf(toDo.getPriority().toUpperCase()).name());
		
		if(toDo.getStatus()!=null && !toDo.getStatus().isEmpty())
			task.get().setStatus(Status.valueOf(toDo.getStatus().toUpperCase()).name());

		if(task.get().getTargetTimestamp()!=null) 
			task.get().setTargetTimestamp(toDo.getTargetTimestamp()); 
		
		if(task.isPresent()) { 
			task.get().setUpdatedTimestamp(LocalDateTime.now()); 
			toDo.setUpdatedTimestamp(task.get().getUpdatedTimestamp()); 
		}
		
		taskRepository.save(task.get());
		return toDo;
	}

	/**
	 * Deletes a ToDo task by its ID.
	 *
	 * @param id The ID of the ToDo task to delete.
	 * @return A message indicating the result of the deletion operation.
	 * @throws EmptyResultDataAccessException If the task with the specified ID is not found.
	 */
	@Override
	@Operation(summary = "Delete a ToDo by ID")
	public String deleteToDo(int id) {

		Optional<Tasks> task = taskRepository.findById(id);

		if(!task.isPresent())
			throw new EmptyResultDataAccessException("Task with ID "+ id +" not found.", id);
			
		taskRepository.deleteById(id);
		return "The user has been deleted";
	}

}
