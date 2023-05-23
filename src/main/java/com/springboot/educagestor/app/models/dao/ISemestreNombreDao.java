package com.springboot.educagestor.app.models.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.educagestor.app.models.entity.SemestreNombre;

public interface ISemestreNombreDao extends JpaRepository<SemestreNombre, Byte>{

	public SemestreNombre findByAcronimo(String acronimo);
	
	@Query("SELECT DISTINCT sm.acronimo from SemestreNombre sm join  sm.listProfesorMateria lpm join  lpm.listAlumnoMateria lam where lam.alumno.alumnoId=?1")
	public Set<String> findSemestresByAlumnoId(String alumnoId);
}
