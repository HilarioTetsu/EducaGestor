package com.springboot.educagestor.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.educagestor.app.models.dto.AlumnoMateriaTablaDTO;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IPersonaService;
import com.springboot.educagestor.app.models.services.ISemestreNombreService;


@Controller
public class AlumnoController {

	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private ISemestreNombreService semestreService;

	@GetMapping("/alumno")
	public String alumno(@RequestParam("alumnoId") String personaId,
			@RequestParam(name = "semestre", required = false) Byte sems, Model model) {

		Persona persona = personaService.findOne(personaId);

		if (!persona.getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}

		List<AlumnoMateriaTablaDTO> listMaterias = new ArrayList<>();

		Set<Byte> setSemestresId = new LinkedHashSet<Byte>();

		Persona personaProfesor = new Persona();
		Materia materia = new Materia();
		SemestreNombre semestre = new SemestreNombre();

		List<AlumnoMateria> listAlumnoMaterias = persona.getAlumno().getListAlumnoMateria();

		for (AlumnoMateria item : listAlumnoMaterias) {

			personaProfesor = item.getProfesorMateria().getProfesor().getPersona();
			materia = item.getProfesorMateria().getMateria();
			semestre = item.getProfesorMateria().getSemestreNombre();

			AlumnoMateriaTablaDTO datosMateria = new AlumnoMateriaTablaDTO();

			datosMateria.setMateriaId(materia.getMateriaId());
			datosMateria.setMateriaNombre(materia.getNombre());

			datosMateria.setNombreProfesor(personaService.getFullName(personaProfesor));
			datosMateria.setSemestreNombreId(semestre.getSemestreNombreId());
			datosMateria.setNombreSemestre(semestre.getAcronimo());
			setSemestresId.add(semestre.getSemestreNombreId());

			listMaterias.add(datosMateria);

		}

		List<Byte> listSemestreId = new ArrayList<Byte>(setSemestresId);

		listSemestreId.sort(Comparator.naturalOrder());

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
			model.addAttribute("currentSemestre",semestreService.findById(sems));

		} else {

			listMaterias.subList(1, listMaterias.size()).clear();
			model.addAttribute("listMaterias", listMaterias);
			model.addAttribute("listSemestres", listSemestres);
			model.addAttribute("currentSemestre",semestreService.findById(listMaterias.get(0).getSemestreNombreId()));
		}

		logger.info(listMaterias.toString());
		logger.info(listSemestreId.toString());

		// model.addAttribute("listMaterias",listMaterias);
		// model.addAttribute("listSemestres", listSemestres);
		model.addAttribute("alumnoNombre", personaService.getFullName(persona));

		return "alumno";
	}

}
