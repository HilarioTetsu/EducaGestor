package com.springboot.educagestor.app.models.services;



import com.springboot.educagestor.app.models.entity.Materia;

public interface IMateriaService {

	public Materia findByMateriaId(String materiaId);
	
}
