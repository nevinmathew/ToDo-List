package com.todo.app.service;

import java.util.List;

import com.todo.app.dto.ToDoDto;


public interface IToDoService {

	public List<ToDoDto> showToDos();
	
	public ToDoDto getToDo(int id);
	
	public String createToDo(ToDoDto toDto);
	
	public ToDoDto updateToDo(int id, ToDoDto toDo);
	
	public String deleteToDo(int id);
	
}
