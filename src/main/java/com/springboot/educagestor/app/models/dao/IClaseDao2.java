package com.springboot.educagestor.app.models.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.dto.ClaseHorarioDTO;
import com.springboot.educagestor.app.models.entity.Clase;

public interface IClaseDao2 extends CrudRepository<Clase, Integer>{

	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.ClaseHorarioDTO(pm.materia.nombre,c.aula,c.horario.horario,c.horario.diaSemana ) " +
	           "FROM AlumnoMateria am " +
	           "JOIN am.profesorMateria pm " +
	           "JOIN pm.listClase c " +
	           "JOIN pm.semestreNombre sn " +
	           "WHERE sn.acronimo = ?1 AND am.alumno.alumnoId = ?2")
	    List<ClaseHorarioDTO> findClaseHorarioBySemestreAcronimoYAlumnoId(String acronimoSemestre, String alumnoId);
	
}
