package com.springboot.educagestor.app.models.services;

import java.util.List;

import com.springboot.educagestor.app.models.entity.SemestreNombre;

public interface ISemestreNombreService {

	public List<SemestreNombre> findByIds(List<Byte> id);
	public SemestreNombre findById(Byte id);
	
}
