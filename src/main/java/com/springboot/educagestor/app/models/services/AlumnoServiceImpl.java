package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.educagestor.app.models.dao.IAlumnoDao;
import com.springboot.educagestor.app.models.dto.AlumnoMateriaTablaDTO;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.entity.Persona;
import com.springboot.educagestor.app.models.entity.SemestreNombre;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IAlumnoDao alumnoDao;
	
	private final Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);
	
	
	@Override
	public Map<String, Object> getListMateriasDetailsAndSemestreIds(List<AlumnoMateria> listAlumnoMaterias) {
		
		
		
		List<AlumnoMateriaTablaDTO> listMaterias = new ArrayList<>();

		
		Set<Byte> setSemestresId = new LinkedHashSet<Byte>();

		Persona personaProfesor = new Persona();
		Materia materia = new Materia();
		SemestreNombre semestre = new SemestreNombre();
		
		Map<String,Object> listResult = new HashMap<>();
		
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
		
		listResult.put("setSemestresId", setSemestresId);
		listResult.put("listMaterias", listMaterias);
		
		
		return listResult;
	}

}
