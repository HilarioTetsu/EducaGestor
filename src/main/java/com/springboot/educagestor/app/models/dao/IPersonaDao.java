package com.springboot.educagestor.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.educagestor.app.models.entity.Persona;

public interface IPersonaDao extends JpaRepository<Persona, String>{

	public Persona findByEmail(String email);
	
}
