package com.springboot.educagestor.app.controllers;

import javax.annotation.security.PermitAll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {

	@GetMapping("/")
	@PermitAll
	public String index() {
		
		return "example";
	}
	
}
