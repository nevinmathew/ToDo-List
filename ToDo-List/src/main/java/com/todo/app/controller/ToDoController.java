package com.todo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.dto.ToDoDto;
import com.todo.app.service.IToDoService;

@RestController
@RequestMapping("/todo")
public class ToDoController {

	public ToDoController() {}

	@Autowired
	public IToDoService toDoService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> createToDo(@RequestBody ToDoDto toDo){
		try {
			return toDo!=null ? ResponseEntity.ok(toDoService.createToDo(toDo)) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The data couldn't be saved. Please try again.");
		}
	}
	
	@GetMapping(path = "/get-list")
	public ResponseEntity<?> getToDoList(){
		return ResponseEntity.ok(toDoService.showToDos());
	}
	
	@GetMapping(path = "/{id}")
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
