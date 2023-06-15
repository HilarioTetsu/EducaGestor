package com.springboot.educagestor.app.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.educagestor.app.models.entity.AlumnoMateria;

public interface IAlumnoMateriaDao extends JpaRepository<AlumnoMateria, Integer>{

	
	@Query("SELECT am FROM AlumnoMateria am WHERE am.alumno.alumnoId=?1 AND am.profesorMateria.profesorMateriaId=?2")
	public Optional<AlumnoMateria> findByalumnoIdAndprofesorMateriaId(String alumnoId,Integer profesorMateriaId);
	
}
