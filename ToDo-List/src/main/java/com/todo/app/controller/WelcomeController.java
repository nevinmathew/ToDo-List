package com.todo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	public WelcomeController() {}

	@GetMapping(path = "/")
	public String welcome() {
		return "Welcome!";
	}
}
