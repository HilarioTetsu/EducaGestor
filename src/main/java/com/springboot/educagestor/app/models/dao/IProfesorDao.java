package com.springboot.educagestor.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Profesor;

public interface IProfesorDao extends CrudRepository<Profesor, String>{

	public Profesor findByProfesorId(String profesorId);
	
	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO(m.materiaId,m.nombre) "
			+ "FROM ProfesorMateria pm JOIN pm.materia m "
			+ "JOIN pm.semestreNombre sn "
			+ "WHERE pm.profesor.profesorId=?1 AND sn.acronimo=?2")
	public List<MateriasProfesorTablaDTO> findMateriasByProfesorIdAndAcronimoSemestre(String profesorId,String acronimo);
	
}
