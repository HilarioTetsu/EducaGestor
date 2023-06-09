package com.springboot.educagestor.app.models.services;

import java.util.List;

import com.springboot.educagestor.app.models.dto.ListaAlumnosProfesorTablaDTO;
import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Profesor;

public interface IProfesorService {

	public Profesor findByProfesorId(String profesorId);
	public List<MateriasProfesorTablaDTO> findMateriasByProfesorIdAndAcronimoSemestre(String profesorId,String acronimo);
	public List<ListaAlumnosProfesorTablaDTO> findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(String profesorId,String materiaId,String acronimo,Integer profesorMateriaId);
}
