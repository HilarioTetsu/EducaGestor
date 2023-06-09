package com.springboot.educagestor.app.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.dto.ListaAlumnosProfesorTablaDTO;
import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Profesor;

public interface IProfesorDao extends CrudRepository<Profesor, String>{

	public Profesor findByProfesorId(String profesorId);
	
	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO(m.materiaId,m.nombre,pm.profesorMateriaId) "
			+ "FROM ProfesorMateria pm JOIN pm.materia m "
			+ "JOIN pm.semestreNombre sn "
			+ "WHERE pm.profesor.profesorId=?1 AND sn.acronimo=?2")
	public List<MateriasProfesorTablaDTO> findMateriasByProfesorIdAndAcronimoSemestre(String profesorId,String acronimo);
	
	
	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.ListaAlumnosProfesorTablaDTO(a.alumnoId,CONCAT(a.persona.apellidoPaterno,' ',a.persona.apellidoMaterno,' ',a.persona.nombre)) "
			+ "FROM Alumno a JOIN a.listAlumnoMateria lam "
			+ "JOIN lam.profesorMateria pm "
			+ "JOIN pm.semestreNombre sn "
			+ "WHERE pm.profesor.profesorId=?1 AND pm.materia.materiaId=?2 "
			+ "AND sn.acronimo=?3 AND pm.profesorMateriaId=?4")
	public List<ListaAlumnosProfesorTablaDTO> findAlumnosByProfesorIdAndMateriaIdAndSemestreAndProfesorMateriaId(String profesorId,String materiaId,String acronimo,Integer profesorMateriaId);
}
