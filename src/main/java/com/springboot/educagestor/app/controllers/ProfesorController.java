package com.springboot.educagestor.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Profesor;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IPersonaService;
import com.springboot.educagestor.app.models.services.IProfesorService;
import com.springboot.educagestor.app.models.services.ISemestreNombreService;

@Controller
public class ProfesorController {

	private final Logger logger = LoggerFactory.getLogger(ProfesorController.class);
	
	@Autowired
	private IProfesorService profesorService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired 
	private ISemestreNombreService semestreService;
	
	@GetMapping("/profesor")
	public String indexProfesor(@RequestParam(name ="Id", required = false) String profesorId
			,@RequestParam(name ="semestre", required = false) String acronimo
			,Model model) {
		
		Profesor profesor =new Profesor();
		SemestreNombre semestreProf =new SemestreNombre();
		
		List<MateriasProfesorTablaDTO> materiasProfesor = new ArrayList<MateriasProfesorTablaDTO>();
		
		if (profesorId==null) {
			profesor=personaService.findByEmail(personaService.getCurrentUserName()).getProfesor();
			profesorId=profesor.getProfesorId();
		}else {
			profesor = profesorService.findByProfesorId(profesorId);
			
		}
		
		if (profesor==null) {
			model.addAttribute("textoError", "Usuario no existe");
			return "error_404";
		}

		if (!profesor.getPersona().getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}
		
		Set<Byte> setSemestresProfesor= semestreService.findSemestresByProfesorId(profesorId);
		
		List<Byte> listSemestresIdProfesor = new ArrayList<>(setSemestresProfesor);
		
		listSemestresIdProfesor.sort(Comparator.naturalOrder());
		
		List<SemestreNombre> listSemestre = semestreService.findByIds(listSemestresIdProfesor);
		
		logger.info(listSemestresIdProfesor.toString());
		
		if (acronimo==null) {
			
			semestreProf=semestreService.findById(listSemestresIdProfesor.get(0));
			
			materiasProfesor=profesorService.findMateriasByProfesorIdAndAcronimoSemestre(profesorId, semestreProf.getAcronimo());
			
			model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
			model.addAttribute("profesorId", profesor.getProfesorId());
			model.addAttribute("currentSemestre", semestreProf);
			model.addAttribute("listSemestres", listSemestre);
			model.addAttribute("materiasProfesor", materiasProfesor);
			return "profesorIndex";
		}
		
		
		return "profesorIndex";
	}
	
	
}
