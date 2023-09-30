package com.todo.app.service;

import java.util.List;

import com.todo.app.dto.ToDoDto;
import com.todo.app.repository.projections.ToDoProjection;


public interface IToDoService {

	public List<ToDoProjection> showToDos();
	
	public ToDoProjection getToDo(int id);
	
	public String createToDo(ToDoDto toDto);
	
	public ToDoDto updateToDo(int id, ToDoDto toDo);
	
	public String deleteToDo(int id);
	
}
