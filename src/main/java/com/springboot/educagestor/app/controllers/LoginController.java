package com.springboot.educagestor.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout, Model model,
			Principal principal) {

		if (principal != null)
			return "redirect:/";

		if (error != null)
			model.addAttribute("error", "Error en el login: username o contraseña incorrecta");
		
		if (logout != null)
			model.addAttribute("logout", "Cierre de sesion con exito");

		return "login";
	}
}
