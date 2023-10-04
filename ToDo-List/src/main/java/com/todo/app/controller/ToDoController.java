package com.todo.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This controller provides operations for managing ToDos.
 */
@OpenAPIDefinition(info = @Info(title = "ToDo API", description = "Operations related to ToDo management"),
	    tags = { @Tag(name = "ToDo") } )
@RestController
@RequestMapping("/todo")
public class ToDoController {

	public ToDoController() {}

	@Autowired
	public IToDoService toDoService;
	
	/**
	 * Creates a new ToDo task.
	 *
	 * @param toDo The ToDoDto object representing the task to be created.
	 * @return ResponseEntity with the result of the creation operation.
	 */
    @Operation(summary = "Create a new ToDo")
	@PostMapping(path = "/create")
    public ResponseEntity<?> createToDo( @Parameter(name= "ToDo information", required = true) @Valid @RequestBody ToDoDto toDo, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            return toDo != null ? ResponseEntity.ok(toDoService.createToDo(toDo)) : ResponseEntity.noContent().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The data couldn't be saved. Please try again.");
        }
    }
	
    /**
     * Retrieves a list of all ToDo tasks.
     *
     * @return ResponseEntity containing a list of ToDo tasks.
     */
    @Operation(summary = "Get a list of ToDos")
    @GetMapping(path = "/get-list")
	public ResponseEntity<?> getToDoList(){
		return ResponseEntity.ok(toDoService.showToDos());
	}
	
    /**
     * Retrieves a ToDo task by its ID.
     *
     * @param id The ID of the ToDo task to retrieve.
     * @param toDo the updated ToDo information
     * @return ResponseEntity with the retrieved ToDo task.
     */
    @Operation(summary = "Get a ToDo by ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getToDo(
            @Parameter(name = "ToDo ID", required = true) @PathVariable("id") int id) {
		return ResponseEntity.ok(toDoService.getToDo(id));
	}
	
    /**
     * Updates a ToDo task by its ID.
     *
     * @param id   The ID of the ToDo task to update.
     * @param toDo The ToDoDto object with updated task information.
     * @return ResponseEntity with the updated ToDo task.
     */
    @Operation(summary = "Update a ToDo by ID")
    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<?> updateToDo(
            @Parameter(name = "ToDo ID", required = true) @PathVariable("id") int id,
            @Parameter(name = "Updated ToDo information", required = true) @Valid @RequestBody ToDoDto toDo,
            BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(toDoService.updateToDo(id, toDo));
    }

    /**
     * Deletes a ToDo task by its ID.
     *
     * @param id The ID of the ToDo task to delete.
     * @return ResponseEntity with the result of the deletion operation.
     */
    @Operation(summary = "Delete a ToDo by ID")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteToDo(
            @Parameter(name = "ToDo ID", required = true) @PathVariable("id") int id) {
		return ResponseEntity.ok(toDoService.deleteToDo(id));
	}
}
