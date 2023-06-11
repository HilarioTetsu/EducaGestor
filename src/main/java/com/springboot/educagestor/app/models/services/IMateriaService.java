package com.springboot.educagestor.app.models.services;



import java.util.List;

import com.springboot.educagestor.app.models.entity.Materia;

public interface IMateriaService {

	public Materia findByMateriaId(String materiaId);
	
	public List<String> findMateriaNombresBySemestreAcronimoAndAlumnoId(String acronimo,String alumnoId);
	
	public List<String> getListUnidades(Byte unidades);
	
}
