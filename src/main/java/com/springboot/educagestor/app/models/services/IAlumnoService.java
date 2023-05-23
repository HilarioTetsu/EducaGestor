package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.springboot.educagestor.app.models.entity.Alumno;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;

public interface IAlumnoService {

	public Map<String,Object> getListMateriasDetailsAndSemestreIds(List<AlumnoMateria> listAlumnoMaterias);
	public Optional<Alumno> findByAlumnoId(String alumnoId);
	
}
