package com.springboot.educagestor.app.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.educagestor.app.models.dto.AlumnoMateriaTablaDTO;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;
import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.models.services.IPersonaService;

@Controller
public class AlumnoController {

	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	
    @Autowired
    private IPersonaService personaService;
	
	@GetMapping("/alumno")
	public String alumno(@RequestParam("alumnoId") String personaId,Model model) {

		Persona persona=personaService.findOne(personaId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
	
		if (!persona.getEmail().equals(currentUserName)) {
			return "error_403";
		}
		
		StringBuffer sb=new StringBuffer();
		
		List<AlumnoMateriaTablaDTO> listMaterias=new ArrayList<>();
		
		for (AlumnoMateria item : persona.getAlumno().getListAlumnoMateria()) {
			
			AlumnoMateriaTablaDTO datosMateria=new AlumnoMateriaTablaDTO();
			datosMateria.setMateriaId(item.getProfesorMateria().getMateria().getMateriaId());
			datosMateria.setMateriaNombre(item.getProfesorMateria().getMateria().getNombre());
			sb.setLength(0);
			sb.append(item.getProfesorMateria().getProfesor().getPersona().getNombre());
			sb.append(" ");
			sb.append(item.getProfesorMateria().getProfesor().getPersona().getApellidoPaterno());
			sb.append(" ");
			sb.append(item.getProfesorMateria().getProfesor().getPersona().getApellidoMaterno());
			datosMateria.setNombreProfesor(sb.toString());
			
			listMaterias.add(datosMateria);
			
		}
		
		model.addAttribute("listMaterias",listMaterias);
		
		
		return "alumno";
	}
	
}
