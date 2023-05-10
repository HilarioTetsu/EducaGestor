package com.springboot.educagestor.app.models.services;

import com.springboot.educagestor.app.models.entity.Persona;

public interface IPersonaService {

	
	public Persona findOne(String id);
	
	public Persona findByEmail(String email);
	
	public String getFullName(Persona persona);
	
	public String getCurrentUserName();
}
