package com.springboot.educagestor.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.entity.Materia;

public interface IMateriaDao extends CrudRepository<Materia, String >{

	public Optional<Materia> findByMateriaId(String materiaId);
	
	@Query("SELECT m.materiaId FROM Materia m JOIN m.listProfesorMateria lpm "
			+ "JOIN lpm.semestreNombre sn "
			+ "JOIN lpm.listAlumnoMateria lam "
			+ "WHERE sn.acronimo=?1 AND lam.alumno.alumnoId=?2")
	public List<String> findMateriaNombresBySemestreAcronimoAndAlumnoId(String acronimo,String alumnoId);
	
}
