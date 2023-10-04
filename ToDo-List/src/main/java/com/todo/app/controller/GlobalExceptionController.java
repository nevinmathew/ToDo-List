package com.todo.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@ControllerAdvice
@OpenAPIDefinition(info = @Info(title = "Global Exception Controller", description = "Handles global exceptions in the ToDo API"))
public class GlobalExceptionController {

	 /**
     * Generic exception handler
     *
     * @param ex The exception that occurred.
     * @return ResponseEntity with an error message and HTTP status 500 (Internal Server Error).
     */
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}
