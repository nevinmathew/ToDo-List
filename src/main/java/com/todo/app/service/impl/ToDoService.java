package com.todo.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.app.dto.ToDoDto;
import com.todo.app.entity.Tasks;
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
	public String createToDo() {
		Tasks task = new Tasks();
		task.setTaskName(task.getTaskName());
		task.setDescription(task.getDescription());
		task.setPriority(task.getPriority());
		task.setStatus(task.getStatus());
		task.setCreatedTimestamp(LocalDateTime.now());
		
		if(task.getTargetTimestamp()!=null)
			task.setTargetTimestamp(task.getTargetTimestamp());
		
		return null;
	}

	@Override
	public ToDoDto updateToDo(int id, ToDoDto toDo) {
		Optional<Tasks> task = taskRepo.findById(id);
		task.get().setTaskName(task.get().getTaskName());
		task.get().setDescription(task.get().getDescription());
		task.get().setPriority(task.get().getPriority());
		task.get().setStatus(task.get().getStatus());
		task.get().setUpdatedTimestamp(LocalDateTime.now());
		
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
