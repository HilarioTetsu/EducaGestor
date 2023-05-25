package com.springboot.educagestor.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.entity.Materia;

public interface IMateriaDao extends CrudRepository<Materia, String >{

	public Optional<Materia> findByMateriaId(String materiaId);
	
}
