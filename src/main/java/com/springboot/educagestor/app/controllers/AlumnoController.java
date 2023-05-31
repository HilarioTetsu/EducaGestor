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
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.educagestor.app.models.dto.AlumnoMateriaTablaDTO;
import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.dto.PerfilAlumnoDTO;
import com.springboot.educagestor.app.models.entity.Alumno;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.models.entity.SemestreNombre;
import com.springboot.educagestor.app.models.services.IAlumnoService;
import com.springboot.educagestor.app.models.services.ICalificacionService;
import com.springboot.educagestor.app.models.services.IClaseService;
import com.springboot.educagestor.app.models.services.IMateriaService;
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
	
	@Autowired
	private IClaseService claseService;
	
	@Autowired
	private ICalificacionService califService;
	
	@Autowired
	private IMateriaService materiaService;

	@GetMapping("/alumno")
	public String alumno(@RequestParam(name ="alumnoId", required = false) String personaId,
			@RequestParam(name = "semestre", required = false) Byte sems, Model model) {

		Persona persona =new Persona();
		
		if (personaId==null) {
			persona=personaService.findByEmail(personaService.getCurrentUserName());
		}else {
			persona = personaService.findOne(personaId);
		}
		
	
		
		if (persona==null) {
			model.addAttribute("textoError", "Usuario no existe");
			return "error_404";
		}

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
			model.addAttribute("alumnoId", persona.getAlumno().getAlumnoId());
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

			//listMaterias.subList(1, listMaterias.size()).clear();
			Iterator<AlumnoMateriaTablaDTO> iterador = listMaterias.iterator();

			while (iterador.hasNext()) {
				AlumnoMateriaTablaDTO alumnoMateriaTablaDTO = (AlumnoMateriaTablaDTO) iterador.next();
				if (alumnoMateriaTablaDTO.getSemestreNombreId() != listSemestres.get(0).getSemestreNombreId()) {
					iterador.remove();
				}
			}
			
			model.addAttribute("listMaterias", listMaterias);
			model.addAttribute("listSemestres", listSemestres);
			model.addAttribute("currentSemestre", semestreService.findById(listMaterias.get(0).getSemestreNombreId()));
		}

		logger.info(listMaterias.toString());
		logger.info(listSemestreId.toString());

		model.addAttribute("alumnoNombre", personaService.getFullName(persona));
		model.addAttribute("personaId", persona.getPersonaId());
		model.addAttribute("alumnoId", persona.getAlumno().getAlumnoId());
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
	
	@GetMapping("alumno/horario/{semestre}/{alumnoId}")
	public String verHorario(@PathVariable(value="semestre") String semestre,@PathVariable(value="alumnoId") String alumnoId,Model model) {
		
		//servicio que obtenga los todos los semestres cursados por un alumno, para comparar con parametro y
		//retornar 404: HECHO
		
		
		Alumno alumno=alumnoService.findByAlumnoId(alumnoId);
		
		if (alumno==null) {
			model.addAttribute("textoError", "AlumnoId incorrecto o no existe");
			return "error_404";
		}
		Set<String> listSemestreByAlumno=semestreService.findSemestresByAlumnoId(alumnoId);
		
		String alumnoNombre=personaService.getFullName(alumno.getPersona());
		
		if (!alumno.getPersona().getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}
		
		if (!listSemestreByAlumno.contains(semestre)) {
			model.addAttribute("textoError", "El alumno no ha cursado ese semestre");
			return "error_404";
		}
		
		String[][] matrizHorario=claseService.getMatrixHorarioByAcronimoSemestreAndAlumnoId(semestre, alumnoId);
		
		
		
		
		
		model.addAttribute("matrizHorario", matrizHorario);
		model.addAttribute("semestre", semestreService.findByAcronimo(semestre).getSemestre());
		model.addAttribute("nombreAlumno", alumnoNombre);
		return "horario";
	}
	
	
	@GetMapping("/alumno/calificaciones")
	public String verCalificaciones(@RequestParam(name="semestre",required = false) String semestre,
			@RequestParam(name="alumnoId",required = false) String alumnoId,
			@RequestParam(name="materiaId",required = false) String materiaId,Model model) {
		//Cambiar parametros al tipo no requerido, en caso de no incluirlos redirigir a vista calificaciones
		//por semestre y exportar pdf
		
		
		if (semestre==null||alumnoId==null||materiaId==null) {
			Persona persona=personaService.findByEmail(personaService.getCurrentUserName());
			Alumno alumno=persona.getAlumno();
			
			Set<String> listSemestreByAlumno=semestreService.findSemestresByAlumnoId(alumno.getAlumnoId());
			
			model.addAttribute("alumnoNombre", personaService.getFullName(persona));
			model.addAttribute("alumnoId", alumno.getAlumnoId());
			model.addAttribute("listSemestres", listSemestreByAlumno);
			
			return "calificacionesSemestre";
			 
		}
		
		Alumno alumno=alumnoService.findByAlumnoId(alumnoId);
		
		if (alumno==null) {
			model.addAttribute("textoError", "AlumnoId incorrecto o no existe");
			return "error_404";
		}
			
		
		if (!alumno.getPersona().getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}
		
		
		List<CalificacionDTO> listCalif=new ArrayList<CalificacionDTO>();
		listCalif=califService.findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(semestre, alumnoId, materiaId);
		
		 if (listCalif.size()==0) {
				model.addAttribute("textoError", "No se encuentran calificaciones con los parametros dados");
				return "error_404";
		}
		
		 Materia materia=materiaService.findByMateriaId(materiaId);
		 
		 String alumnoNombre=personaService.getFullName(alumno.getPersona());
		 
		 model.addAttribute("listCalificaciones", listCalif);
		 model.addAttribute("semestre", semestreService.findByAcronimo(semestre).getSemestre());
		 model.addAttribute("nombreAlumno", alumnoNombre);
		 model.addAttribute("materiaNombre", materia.getNombre());
		 model.addAttribute("alumnoId", alumno.getAlumnoId());
		
		return "calificacionesMateria";
	}
	
	
	@PostMapping("/alumno/calificaciones")
	public String calificacionesMateriaPost(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String semestre = request.getParameter("selectSemestre");
		String alumnoId = request.getParameter("alumnoId");
		
		redirectAttributes.addAttribute("alumnoId", alumnoId);
		redirectAttributes.addAttribute("semestre", semestre);
		redirectAttributes.addAttribute("format", "pdf");
		
		return "redirect:/alumno/calificaciones/exportar";
	}
	
	
	@GetMapping("/alumno/calificaciones/exportar")
	public String exportarCalificacionesSemestre(@RequestParam(name="semestre",required = false) String semestreAcronimo,
			@RequestParam(name="alumnoId",required = false) String alumnoId,
			Model model) {
		int maxUnidades=0;
		Alumno alumno=alumnoService.findByAlumnoId(alumnoId);
		
		if (alumno==null) {
			model.addAttribute("textoError", "AlumnoId incorrecto o no existe");
			return "error_404";
		}
		
		if (!alumno.getPersona().getEmail().equals(personaService.getCurrentUserName())) {
			return "error_403";
		}
		
		String alumnoNombre=personaService.getFullName(alumno.getPersona());
		
		List<String> listCalif=materiaService.findMateriaNombresBySemestreAcronimoAndAlumnoId(semestreAcronimo, alumnoId);
		Map<String, List<CalificacionDTO>>mapResult=califService.findCalificacionesSemestreByMateriasAndSemestreAcronimoAndAlumnoId(listCalif,"ENE20-JUL20", "LG1234");
		
		
		for (Map.Entry<String, List<CalificacionDTO>> entry : mapResult.entrySet()) {
			if (entry.getValue().size()>maxUnidades) {
				maxUnidades=entry.getValue().size();
			}
		}
		
		model.addAttribute("mapCalificaciones", mapResult);
		model.addAttribute("alumnoNombre", alumnoNombre);
		model.addAttribute("semestreNombreCompleto", semestreService.findByAcronimo(semestreAcronimo).getSemestre());
		model.addAttribute("carreraAlumno", alumno.getCarrera().getDescripcion());
		model.addAttribute("maxUnidades", maxUnidades);
		
		return "calificacionesSemestrePDF";
	}
	
	@GetMapping("/alumno/perfil/{alumnoId}")
	public String verPerfil(@PathVariable("alumnoId") String alumnoId,Model model) {
		
		PerfilAlumnoDTO perfilAlumno=alumnoService.findPerfilAlumnoByAlumnoId(alumnoId);
		
		 model.addAttribute("alumnoId", alumnoId);
		 model.addAttribute("perfilAlumno", perfilAlumno);
		
		return "perfilAlumno";
	}
	
	@PostMapping("/alumno/perfil")
	public String actualizarPerfil(@Valid @ModelAttribute("perfilAlumno") PerfilAlumnoDTO perfilAlumno,BindingResult result,SessionStatus status,Model model) {
		
		if(result.hasErrors()) {			
			return "perfilAlumno";
		}
		
		Persona persona=alumnoService.findByAlumnoId(perfilAlumno.getAlumnoId()).getPersona();
		
		persona.setDireccion(perfilAlumno.getDireccion());
		persona.setCodigoPostal(perfilAlumno.getCodigoPostal());
		persona.setTelefono(perfilAlumno.getTelefono());
		persona.setUsuarioModificacion(perfilAlumno.getAlumnoId());
		
		personaService.save(persona);
		
		status.setComplete();
		return "redirect:/alumno";
	}

}
