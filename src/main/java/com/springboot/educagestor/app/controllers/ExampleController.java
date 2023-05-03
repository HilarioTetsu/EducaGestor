package com.springboot.educagestor.app.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {

	@GetMapping("/")
	public String index() {
		
		return "example";
	}
	
	@GetMapping("/alumno")
	public String login() {
		
		return "example";
	}
	
}
