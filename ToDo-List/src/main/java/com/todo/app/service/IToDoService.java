package com.todo.app.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.todo.app.dto.ToDoDto;
import com.todo.app.repository.projections.ToDoProjection;


public interface IToDoService {

	public CompletableFuture<List<ToDoProjection>> showToDos();
	
	public CompletableFuture<ToDoProjection> getToDo(int id);
	
	public String createToDo(ToDoDto toDto);
	
	public ToDoDto updateToDo(int id, ToDoDto toDo);
	
	public CompletableFuture<String> deleteToDo(int id);
	
}
