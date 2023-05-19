package com.springboot.educagestor.app.controllers;

import java.util.ArrayList;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.educagestor.app.models.dto.AlumnoMateriaTablaDTO;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;

import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IAlumnoService;
import com.springboot.educagestor.app.models.services.IPersonaService;
import com.springboot.educagestor.app.models.services.ISemestreNombreService;

@Controller
public class AlumnoController {


	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private ISemestreNombreService semestreService;
	
	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/alumno")
	public String alumno(@RequestParam("alumnoId") String personaId,
			@RequestParam(name = "semestre", required = false) Byte sems, Model model) {

		Persona persona = personaService.findOne(personaId);

		if (!persona.getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}

		List<AlumnoMateriaTablaDTO> listMaterias = new ArrayList<>();

		Set<Byte> setSemestresId = new LinkedHashSet<Byte>();


		List<AlumnoMateria> listAlumnoMaterias = new ArrayList<AlumnoMateria>();

		if (persona.getAlumno() == null) {
			model.addAttribute("textoError", "Usuario no esta vinculado a ningun alumno");
			return "error_404";
		}

		listAlumnoMaterias = persona.getAlumno().getListAlumnoMateria();

		if (listAlumnoMaterias.size() == 0) {
			model.addAttribute("alumnoNombre", personaService.getFullName(persona));
			model.addAttribute("personaId", persona.getPersonaId());
			model.addAttribute("currentSemestre", new SemestreNombre());
			return "alumno";
		}

		
		
		Map<String ,Object> listMateriaDetails=new HashMap<>();
		 listMateriaDetails=alumnoService.getListMateriasDetailsAndSemestreIds(listAlumnoMaterias);

		setSemestresId=(Set<Byte>) listMateriaDetails.get("setSemestresId");
		listMaterias=(List<AlumnoMateriaTablaDTO>) listMateriaDetails.get("listMaterias");
		
		List<Byte> listSemestreId = new ArrayList<Byte>(setSemestresId);

		listSemestreId.sort(Comparator.naturalOrder());
		listMaterias.sort(Comparator.comparing(AlumnoMateriaTablaDTO::getSemestreNombreId));

		List<SemestreNombre> listSemestres = semestreService.findByIds(listSemestreId);

		if (sems != null) {

			Iterator<AlumnoMateriaTablaDTO> iterador = listMaterias.iterator();

			while (iterador.hasNext()) {
				AlumnoMateriaTablaDTO alumnoMateriaTablaDTO = (AlumnoMateriaTablaDTO) iterador.next();
				if (alumnoMateriaTablaDTO.getSemestreNombreId() != sems) {
					iterador.remove();
				}
			}

			model.addAttribute("listMaterias", listMaterias);
			model.addAttribute("listSemestres", listSemestres);
			model.addAttribute("currentSemestre", semestreService.findById(sems));

		} else {

			listMaterias.subList(1, listMaterias.size()).clear();
			model.addAttribute("listMaterias", listMaterias);
			model.addAttribute("listSemestres", listSemestres);
			model.addAttribute("currentSemestre", semestreService.findById(listMaterias.get(0).getSemestreNombreId()));
		}

		logger.info(listMaterias.toString());
		logger.info(listSemestreId.toString());

		model.addAttribute("alumnoNombre", personaService.getFullName(persona));
		model.addAttribute("personaId", persona.getPersonaId());
		return "alumno";
	}

	@PostMapping("/alumno")
	public String alumnoSelectSemestre(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Byte semestre = Byte.parseByte(request.getParameter("selectSemestre"));
		String personaId = request.getParameter("id");

		redirectAttributes.addAttribute("semestre", semestre);
		redirectAttributes.addAttribute("alumnoId", personaId);
		return "redirect:/alumno";
	}
	
	@GetMapping("/horario/{semestre}")
	public String verHorario(@PathVariable(value="semestre") String semestre,Model model) {
		
		return "horario";
	}

}
