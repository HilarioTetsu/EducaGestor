package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.springboot.educagestor.app.models.dto.PerfilAlumnoDTO;
import com.springboot.educagestor.app.models.entity.Alumno;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;

public interface IAlumnoService {

	public Map<String,Object> getListMateriasDetailsAndSemestreIds(List<AlumnoMateria> listAlumnoMaterias);
	
	public Alumno findByAlumnoId(String alumnoId);
	
	public PerfilAlumnoDTO findPerfilAlumnoByAlumnoId(String alumno);
	
	public void guardarAlumno(Alumno alumno);
	
}
