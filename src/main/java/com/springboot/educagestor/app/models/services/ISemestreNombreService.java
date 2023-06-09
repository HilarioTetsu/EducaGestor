package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Set;

import com.springboot.educagestor.app.models.entity.SemestreNombre;

public interface ISemestreNombreService {

	public List<SemestreNombre> findByIds(List<Byte> id);
	public SemestreNombre findById(Byte id);
	public SemestreNombre findByAcronimo(String acronimo);
	public Set<String> findSemestresByAlumnoId(String alumnoId);
	public Set<Byte> findSemestresByProfesorId(String profesorId);
}
