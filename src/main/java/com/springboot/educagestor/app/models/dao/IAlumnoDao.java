package com.springboot.educagestor.app.models.dao;



import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.educagestor.app.models.dto.PerfilAlumnoDTO;
import com.springboot.educagestor.app.models.entity.Alumno;


public interface IAlumnoDao extends JpaRepository<Alumno, String>{

	
	public Optional<Alumno> findByAlumnoId(String alumnoId);
	
	public Boolean existsByAlumnoId(String alumnoId);
	
	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.PerfilAlumnoDTO(CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre),a.alumnoId,c.descripcion,pe.nombre,a.generacion,p.email,p.direccion,p.codigoPostal,p.telefono) "
			+ "FROM Alumno a JOIN a.persona p "
			+ "JOIN a.carrera c "
			+ "JOIN a.planEstudios pe WHERE a.alumnoId = ?1")
	public PerfilAlumnoDTO findPerfilAlumnoByAlumnoId(String alumno);
	
}
