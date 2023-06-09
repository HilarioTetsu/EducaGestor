package com.springboot.educagestor.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.educagestor.app.models.dto.ClaseHorariosProfesorMateriasDTO;
import com.springboot.educagestor.app.models.dto.ListaAlumnosProfesorTablaDTO;
import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.entity.Profesor;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IClaseService;
import com.springboot.educagestor.app.models.services.IMateriaService;
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
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private IClaseService claseService;
	
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
		}else {
			semestreProf=semestreService.findByAcronimo(acronimo);
			
			materiasProfesor=profesorService.findMateriasByProfesorIdAndAcronimoSemestre(profesorId, semestreProf.getAcronimo());
			
			model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
			model.addAttribute("profesorId", profesor.getProfesorId());
			model.addAttribute("currentSemestre", semestreProf);
			model.addAttribute("listSemestres", listSemestre);
			model.addAttribute("materiasProfesor", materiasProfesor);
			return "profesorIndex";
		}
		
				
	}
	
	@PostMapping("/profesor")
	public String postSelectSemestre(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String profesorId=request.getParameter("id");
		String acronimo=request.getParameter("selectSemestre");
		
		redirectAttributes.addAttribute("semestre", acronimo);
		redirectAttributes.addAttribute("Id", profesorId);
		
		
		return "redirect:/profesor";
	}
	
	@GetMapping("/profesor/alumnos")
	public String verAlumnosPorMateria(@RequestParam(name = "semestre") String acronimo,
			@RequestParam(name = "materiaId") String materiaId,
			@RequestParam(name = "Id") String profesorMateriaId,
			Model model) {
		
		int noLista=1;
		
		Profesor profesor =new Profesor();
		Materia materia=new Materia();		
		SemestreNombre semestre=new SemestreNombre();
		
		profesor=personaService.findByEmail(personaService.getCurrentUserName()).getProfesor();
		materia=materiaService.findByMateriaId(materiaId);
		semestre=semestreService.findByAcronimo(acronimo);
		
		List<ClaseHorariosProfesorMateriasDTO> listHorariosMateria=claseService.findClaseHorarioProfesorMateriaByProfesorMateriaId(Integer.parseInt(profesorMateriaId));
		
		List<ListaAlumnosProfesorTablaDTO> listAlumnosProfesor=profesorService.findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(profesor.getProfesorId(), materiaId, acronimo, Integer.parseInt(profesorMateriaId));
		
		
		model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
		model.addAttribute("materia", materia);
		model.addAttribute("semestre", semestre);
		model.addAttribute("listAlumnosProfesor", listAlumnosProfesor);
		model.addAttribute("listHorariosMateria", listHorariosMateria);
		
		return "profesorAlumnos";
	}
	
}