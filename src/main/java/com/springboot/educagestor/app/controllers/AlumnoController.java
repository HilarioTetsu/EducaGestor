package com.springboot.educagestor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlumnoController {

	
	
	
	@GetMapping("/alumno")
	public String alumno(@RequestParam("alumnoId") String personaId,Model model) {

		return "alumno";
	}
	
}
