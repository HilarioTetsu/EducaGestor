package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Map;

import com.springboot.educagestor.app.models.entity.AlumnoMateria;

public interface IAlumnoService {

	public Map<String,Object> getListMateriasDetailsAndSemestreIds(List<AlumnoMateria> listAlumnoMaterias);
	
}
