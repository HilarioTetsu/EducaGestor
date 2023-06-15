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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.dto.ClaseHorariosProfesorMateriasDTO;
import com.springboot.educagestor.app.models.dto.ListaAlumnosProfesorTablaDTO;
import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Calificacion;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.entity.Profesor;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IAlumnoMateriaService;
import com.springboot.educagestor.app.models.services.ICalificacionService;
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

	@Autowired
	private ICalificacionService califService;
	@Autowired
	private IAlumnoMateriaService alumnoMateriaService;

	@GetMapping("/profesor")
	public String indexProfesor(@RequestParam(name = "Id", required = false) String profesorId,
			@RequestParam(name = "semestre", required = false) String acronimo, Model model) {

		Profesor profesor = new Profesor();
		SemestreNombre semestreProf = new SemestreNombre();

		List<MateriasProfesorTablaDTO> materiasProfesor = new ArrayList<MateriasProfesorTablaDTO>();

		if (profesorId == null) {
			profesor = personaService.findByEmail(personaService.getCurrentUserName()).getProfesor();
			profesorId = profesor.getProfesorId();
		} else {
			profesor = profesorService.findByProfesorId(profesorId);

		}

		if (profesor == null) {
			model.addAttribute("textoError", "Usuario no existe");
			return "error_404";
		}

		if (!profesor.getPersona().getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}

		Set<Byte> setSemestresProfesor = semestreService.findSemestresByProfesorId(profesorId);

		List<Byte> listSemestresIdProfesor = new ArrayList<>(setSemestresProfesor);

		listSemestresIdProfesor.sort(Comparator.naturalOrder());

		List<SemestreNombre> listSemestre = semestreService.findByIds(listSemestresIdProfesor);

		if (acronimo == null) {

			semestreProf = semestreService.findById(listSemestresIdProfesor.get(0));

			materiasProfesor = profesorService.findMateriasByProfesorIdAndAcronimoSemestre(profesorId,
					semestreProf.getAcronimo());

			model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
			model.addAttribute("profesorId", profesor.getProfesorId());
			model.addAttribute("currentSemestre", semestreProf);
			model.addAttribute("listSemestres", listSemestre);
			model.addAttribute("materiasProfesor", materiasProfesor);
			return "profesorIndex";
		} else {
			semestreProf = semestreService.findByAcronimo(acronimo);

			materiasProfesor = profesorService.findMateriasByProfesorIdAndAcronimoSemestre(profesorId,
					semestreProf.getAcronimo());

			model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
			model.addAttribute("profesorId", profesor.getProfesorId());
			model.addAttribute("currentSemestre", semestreProf);
			model.addAttribute("listSemestres", listSemestre);
			model.addAttribute("materiasProfesor", materiasProfesor);
			return "profesorIndex";
		}

	}

	@PostMapping("/profesor")
	public String postSelectSemestre(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String profesorId = request.getParameter("id");
		String acronimo = request.getParameter("selectSemestre");

		redirectAttributes.addAttribute("semestre", acronimo);
		redirectAttributes.addAttribute("Id", profesorId);

		return "redirect:/profesor";
	}

	@GetMapping("/profesor/alumnos")
	public String verAlumnosPorMateria(@RequestParam(name = "semestre") String acronimo,
			@RequestParam(name = "materiaId") String materiaId, @RequestParam(name = "Id") String profesorMateriaId,
			Model model) {

		Profesor profesor = new Profesor();
		Materia materia = new Materia();
		SemestreNombre semestre = new SemestreNombre();

		profesor = personaService.findByEmail(personaService.getCurrentUserName()).getProfesor();
		materia = materiaService.findByMateriaId(materiaId);
		semestre = semestreService.findByAcronimo(acronimo);

		List<ClaseHorariosProfesorMateriasDTO> listHorariosMateria = claseService
				.findClaseHorarioProfesorMateriaByProfesorMateriaId(Integer.parseInt(profesorMateriaId));

		List<ListaAlumnosProfesorTablaDTO> listAlumnosProfesor = profesorService
				.findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(profesor.getProfesorId(), materiaId,
						acronimo, Integer.parseInt(profesorMateriaId));

		model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
		model.addAttribute("materia", materia);
		model.addAttribute("semestre", semestre);
		model.addAttribute("listAlumnosProfesor", listAlumnosProfesor);
		model.addAttribute("listHorariosMateria", listHorariosMateria);
		model.addAttribute("pmId", profesorMateriaId);
		return "profesorAlumnos";
	}

	@GetMapping("/profesor/calificaciones/{materiaId}")
	public String profesorCalificaciones(@PathVariable("materiaId") String materiaId,
			@RequestParam("semestre") String acronimo, @RequestParam("Id") String profesorMateriaId, Model model) {

		Profesor profesor = new Profesor();
		Materia materia = new Materia();
		SemestreNombre semestre = new SemestreNombre();

		profesor = personaService.findByEmail(personaService.getCurrentUserName()).getProfesor();
		materia = materiaService.findByMateriaId(materiaId);
		semestre = semestreService.findByAcronimo(acronimo);

		List<String> listUnidades = materiaService.getListUnidades(materia.getUnidades());

		List<ListaAlumnosProfesorTablaDTO> listAlumnosProfesor = profesorService
				.findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(profesor.getProfesorId(), materiaId,
						acronimo, Integer.parseInt(profesorMateriaId));

		List<CalificacionDTO> listCalifAlumno = new ArrayList();

		for (ListaAlumnosProfesorTablaDTO alumno : listAlumnosProfesor) {
			listCalifAlumno = califService.findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(acronimo,
					alumno.getAlumnoId(), materiaId);
			logger.info("TAMAÑO CALIFICACIONES UNIDADES ALUMNO: " + Integer.toString(listCalifAlumno.size()));

			if (listCalifAlumno.size() < materia.getUnidades()) {

				Set<Byte> setUnidadesPresentes = califService.generateSetUnidades(listCalifAlumno);

				for (Byte i = 1; i <= materia.getUnidades(); i++) {

					if (!setUnidadesPresentes.contains(i)) {
						CalificacionDTO califEjemplo = new CalificacionDTO(i.toString());						
						listCalifAlumno.add(califEjemplo);
					}
				}

				
				
			}
			listCalifAlumno.sort(Comparator.comparing(CalificacionDTO::getUnidadNum));
			logger.info("lista calificaciones alumno ORDENADA:"+listCalifAlumno.toString());
			alumno.setCalificaciones(listCalifAlumno);
		}

		model.addAttribute("profesorNombre", personaService.getFullName(profesor.getPersona()));
		model.addAttribute("profesorId", profesor.getProfesorId());
		model.addAttribute("materia", materia);
		model.addAttribute("semestre", semestre);
		model.addAttribute("listAlumnosProfesor", listAlumnosProfesor);
		model.addAttribute("listUnidades", listUnidades);
		model.addAttribute("profesorMateriaId", profesorMateriaId);

		return "profesorCalificaciones";
	}

	@SuppressWarnings("unused")
	@PostMapping("/profesor/calificaciones")
	public String postEnvioCalificaciones(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String profesorMateriaId = request.getParameter("id");
		String acronimo = request.getParameter("semestre");
		String materiaId = request.getParameter("mId");
		String profesorId = request.getParameter("pId");

		Calificacion calif;
		Profesor profesor = profesorService.findByProfesorId(profesorId);
		Materia materia = materiaService.findByMateriaId(materiaId);
		String califActual;

		List<ListaAlumnosProfesorTablaDTO> listAlumnosProfesor = profesorService
				.findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(profesor.getProfesorId(), materiaId,
						acronimo, Integer.parseInt(profesorMateriaId));

		for (ListaAlumnosProfesorTablaDTO alumno : listAlumnosProfesor) {
			for (Byte i = 1; i <= materia.getUnidades(); i++) {

				califActual = request.getParameter(alumno.getAlumnoId().concat("_").concat(i.toString()));
				logger.info("CALIFICACION ACTUAL:" + califActual);
				calif = califService.findCalificacionByAlumnoIdAndUnidadAndProfesorMateriaId(alumno.getAlumnoId(), i,
						Integer.parseInt(profesorMateriaId));
				
				
				try {
					logger.info("CALIFICACION ENCONTRADA DE LA UNIDAD "+i+": "+calif.toString());
				} catch (Exception e) {
					logger.info("CALIFICACION NO ENCONTRADA DE LA UNIDAD "+i+"");
				}
				
				
				if (califActual == null || califActual.isEmpty()) {
					
					if (calif==null) {
						continue;
					}else {
						logger.info("SE BORRA ESTA CALIF: "+calif.toString());
						califService.delete(calif);
						continue;
					}
					
					
				}



				if (calif == null) {
					calif = new Calificacion();
					calif.setCalif(Byte.parseByte(califActual));
					calif.setUnidad(i);
					calif.setAlumnoMateria(alumnoMateriaService.findByalumnoIdAndprofesorMateriaId(alumno.getAlumnoId(),
							Integer.parseInt(profesorMateriaId)));
					calif.setUsuarioCreacion(profesor.getProfesorId());
					
					logger.info("SE AGREGARA UNA NUEVA CALIFICACION: "+calif.toString());
					califService.save(calif);
				} else if (calif.getCalif() != Byte.parseByte(califActual)) {
					calif.setCalif(Byte.parseByte(califActual));
					calif.setUnidad(i);
					calif.setUsuarioModificacion(profesor.getProfesorId());
					logger.info("SE ACTUALIZA UNA CALIFICACION: "+calif.toString());
					califService.save(calif);
				}

			}
		}

		return "redirect:/profesor";
	}

}
