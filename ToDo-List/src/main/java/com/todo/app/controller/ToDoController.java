package com.todo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.dto.ToDoDto;
import com.todo.app.service.IToDoService;

@RestController
public class ToDoController {

	public ToDoController() {}

	@Autowired
	public IToDoService toDoService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> createToDo(@RequestBody ToDoDto toDto){
		return null;
	}
	
	@GetMapping(path = "/get-list")
	public ResponseEntity<?> getToDoList(){
		return ResponseEntity.ok(toDoService.showToDos());
	}
	
	@GetMapping(path = "/todo/{id}")
	public ResponseEntity<?> getToDo(@PathVariable("id") int id){
		return ResponseEntity.ok(toDoService.getToDo(id));
	}
	
	@PatchMapping(path = "/update/{id}")
	public ResponseEntity<?> updateToDo(@PathVariable("id") int id, @RequestBody ToDoDto toDo){
		return ResponseEntity.ok(toDoService.updateToDo(id, toDo));
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteToDo(@PathVariable("id") int id){
		return ResponseEntity.ok(toDoService.deleteToDo(id));
	}
}
