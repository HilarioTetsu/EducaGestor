package com.springboot.educagestor.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.entity.Calificacion;

public interface ICalificacionDao extends CrudRepository<Calificacion, Integer>{

	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.CalificacionDTO(CONCAT('',c.unidad),c.calif) "
			+ "from Calificacion c JOIN  c.alumnoMateria am JOIN  am.profesorMateria pm "
			+ "JOIN  pm.semestreNombre sm WHERE sm.acronimo=?1 AND am.alumno.alumnoId=?2 AND "
			+ "pm.materia.materiaId=?3")
	public List<CalificacionDTO> findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(String acronimo,String alumnoId,String materiaId);
	
	@Query("SELECT calf FROM Calificacion calf JOIN calf.alumnoMateria am JOIN am.profesorMateria pm "
			+ "WHERE am.alumno.alumnoId=?1 AND calf.unidad=?2 AND pm.profesorMateriaId=?3")
	public Optional<Calificacion> findCalificacionByAlumnoIdAndUnidadAndProfesorMateriaId(String alumnoId,Byte unidad, Integer profesorMateriaId);
}
