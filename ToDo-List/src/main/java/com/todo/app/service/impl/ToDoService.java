package com.todo.app.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.app.dto.ToDoDto;
import com.todo.app.entity.Category;
import com.todo.app.entity.Tasks;
import com.todo.app.entity.enums.Priority;
import com.todo.app.entity.enums.Status;
import com.todo.app.repository.CategoryRepository;
import com.todo.app.repository.TaskRepository;
import com.todo.app.repository.projections.ToDoProjection;
import com.todo.app.service.IToDoService;

@Service
public class ToDoService implements IToDoService{

	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
//	@Autowired
//	EntityManager entityManager;
	
	@Override
	public List<ToDoProjection> showToDos() {
		List<ToDoProjection> tasks = taskRepo.findAllTasks();
		
		return tasks.isEmpty() ? Collections.emptyList() : tasks;
	}

	@Override
	public ToDoProjection getToDo(int id) {
		Optional<ToDoProjection> task = taskRepo.findTodoById(id);
		
		return task.isPresent() ? task.get() : null;
	}

	@Override
	@Transactional
	public String createToDo(ToDoDto toDo) {
		Tasks task = new Tasks();
		task.setTaskName(toDo.getTaskName());
		task.setDescription(toDo.getDescription());
		
		if(toDo.getPriority()!=null && !toDo.getPriority().isEmpty())
			switch(toDo.getPriority().toLowerCase()) {
				case "low":
					task.setPriority(Priority.low.name());
					break;
				case "medium":
					task.setPriority(Priority.medium.name());
					break;
				case "high":
					task.setPriority(Priority.high.name());
					break;
			}
		
		if(toDo.getStatus()!=null && !toDo.getStatus().isEmpty())
			switch(toDo.getStatus().toLowerCase()){
				case "pending":
					task.setStatus(Status.pending.name());
					break;
				case "inprogress":
					task.setStatus(Status.inProgress.name());
					break;
				case "completed":
					task.setStatus(Status.completed.name());
					break;
			}
		
		task.setCreatedTimestamp(LocalDateTime.now());
		
		if(task.getTargetTimestamp()!=null)
			task.setTargetTimestamp(toDo.getTargetTimestamp());
		
//		task.setCategory(entityManager.getReference(Category.class, toDo.getCategoryId()));
		task.setCategory(categoryRepo.findById(toDo.getCategoryId()).get());
		
		taskRepo.save(task);
		return "The toDo has been created.";
	}

	@Override
	public ToDoDto updateToDo(int id, ToDoDto toDo) { 
		Optional<Tasks> task = taskRepo.findById(id); 
		task.get().setTaskName(toDo.getTaskName()); 
		task.get().setDescription(toDo.getDescription()); 
		
		if(toDo.getPriority()!=null && !toDo.getPriority().isEmpty()) 
			switch(toDo.getPriority().toLowerCase()) { 
				case "low": 
					task.get().setPriority(Priority.low.name()); 
					toDo.setPriority(Priority.low.name()); 
					break;
				case "medium":
					task.get().setPriority(Priority.medium.name());
					toDo.setPriority(Priority.medium.name());
					break;
				case "high":
					task.get().setPriority(Priority.high.name());
					toDo.setPriority(Priority.high.name());
					break;
			}
		
		if(toDo.getStatus()!=null && !toDo.getStatus().isEmpty())
			switch(toDo.getStatus().toLowerCase()){
				case "pending":
					task.get().setStatus(Status.pending.name());
					toDo.setStatus(Status.pending.name());
					break;
				case "inprogress":
					task.get().setStatus(Status.inProgress.name());
					toDo.setStatus(Status.inProgress.name());
					break;
				case "completed":
					task.get().setStatus(Status.completed.name());
					toDo.setStatus(Status.completed.name());
					break;
			} 

		if(task.get().getTargetTimestamp()!=null) 
			task.get().setTargetTimestamp(toDo.getTargetTimestamp()); 
		
		if(task.isPresent()) { 
			task.get().setUpdatedTimestamp(LocalDateTime.now()); 
			toDo.setUpdatedTimestamp(task.get().getUpdatedTimestamp()); 
		}
		
		taskRepo.save(task.get());
		return toDo;
	}

	@Override
	public String deleteToDo(int id) {
		Optional<Tasks> task = taskRepo.findById(id);
		
		if(task.isPresent()) {
			taskRepo.deleteById(id);
			return "The user has been deleted";
		}

		return "This user does not exist";
	}


}
