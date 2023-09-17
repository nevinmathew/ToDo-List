package com.todo.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.app.dto.ToDoDto;
import com.todo.app.entity.Tasks;
import com.todo.app.entity.enums.Priority;
import com.todo.app.entity.enums.Status;
import com.todo.app.repository.TaskRepository;
import com.todo.app.service.IToDoService;

@Service
public class ToDoService implements IToDoService{

	@Autowired
	TaskRepository taskRepo;
	
	@Override
	public List<ToDoDto> showToDos() { //use projection to set the data from a custom query to the dto with json body
		List<Tasks> tasks = taskRepo.findAll();
		
		return null;
	}

	@Override
	public ToDoDto getToDo(int id) {
		Optional<Tasks> task = taskRepo.findById(id);
		
		return null;
	}

	@Override
	public String createToDo(ToDoDto toDo) {
		Tasks task = new Tasks();
		task.setTaskName(toDo.getTaskName());
		task.setDescription(toDo.getDescription());
		
		switch(toDo.getPriority().toLowerCase()) {
			case "low":
				task.setPriority(Priority.low.name());
			case "medium":
				task.setPriority(Priority.medium.name());
			case "high":
				task.setPriority(Priority.high.name());
		}
		
		switch(toDo.getStatus().toLowerCase()){
			case "pending":
				task.setStatus(Status.pending.name());
			case "inprogress":
				task.setStatus(Status.inProgress.name());
			case "completed":
				task.setStatus(Status.completed.name());
		}
		
		task.setCreatedTimestamp(LocalDateTime.now());
		
		if(task.getTargetTimestamp()!=null)
			task.setTargetTimestamp(toDo.getTargetTimestamp());
		
		return null;
	}

	@Override
	public ToDoDto updateToDo(int id, ToDoDto toDo) {
		Optional<Tasks> task = taskRepo.findById(id);
		task.get().setTaskName(toDo.getTaskName());
		task.get().setDescription(toDo.getDescription());
		
		switch(toDo.getPriority().toLowerCase()) {
		case "low":
			task.get().setPriority(Priority.low.name());
		case "medium":
			task.get().setPriority(Priority.medium.name());
		case "high":
			task.get().setPriority(Priority.high.name());
		}
		
		switch(toDo.getStatus().toLowerCase()){
			case "pending":
				task.get().setStatus(Status.pending.name());
			case "inprogress":
				task.get().setStatus(Status.inProgress.name());
			case "completed":
				task.get().setStatus(Status.completed.name());
		}
	
		task.get().setUpdatedTimestamp(LocalDateTime.now());
		
		if(task.get().getTargetTimestamp()!=null)
			task.get().setTargetTimestamp(toDo.getTargetTimestamp());
		
		if(task.isPresent()) {
			task.get().setUpdatedTimestamp(LocalDateTime.now());
		}
		
		return null;
	}

	@Override
	public String deleteToDo(int id) {
		Optional<Tasks> task = taskRepo.findById(id);
		
		if(task.isPresent()) {
			taskRepo.deleteById(id);
			return "The user has been deleted";
		}
			
		return "The user does not exist";
	}


}
