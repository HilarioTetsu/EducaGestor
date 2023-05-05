package com.springboot.educagestor.app.controllers;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.educagestor.app.models.entity.Persona;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal) {


		
		if (error != null) {
			model.addAttribute("error", "Error en el login: username o contraseña incorrecta");
			return "login";
		}
		if (logout != null) {
			model.addAttribute("logout", "Cierre de sesion con exito");
			return "login";
		}

		
		if (principal != null) {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			// String currentUserName = authentication.getName();

			// Obtener el rol del usuario actualmente autenticado
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			String role = authorities.iterator().next().getAuthority();

			
			logger.info("ESTE ES EL ROLE:".concat(role));
			// Redirigir a la vista correspondiente según el rol del usuario
			if (role.equals("ROLE_ALUMNO")) {
				return "redirect:/alumno";
			} else if (role.equals("ROLE_ADMIN")) {
				return "redirect:/admin";

			}
		}

		return "login";
	}

	@GetMapping("/")
	public String index(Principal principal) {

		if (principal != null) {

			Persona persona=new Persona();
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			// String currentUserName = authentication.getName();

			// Obtener el rol del usuario actualmente autenticado
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			String role = authorities.iterator().next().getAuthority();

			
			logger.info("ESTE ES EL ROLE:".concat(role));
			// Redirigir a la vista correspondiente según el rol del usuario
			if (role.equals("ROLE_ALUMNO")) {
				return "redirect:/alumno";
			} else if (role.equals("ROLE_ADMIN")) {
				return "redirect:/admin";

			}
		}
		
		return "login";
	}

	@GetMapping("/alumno")
	public String alumno() {

		return "example";
	}

	@GetMapping("/admin")
	public String admin() {

		return "admin";
	}
}
