package com.springboot.educagestor.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.entity.Administrador;

public interface IAdministradorDao extends CrudRepository<Administrador, String>{

	public Administrador findByEmailOrUsername(String email,String username);
	
}
