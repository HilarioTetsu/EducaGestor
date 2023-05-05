package com.springboot.educagestor.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IPersonaDao;
import com.springboot.educagestor.app.models.entity.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public Persona findOne(String id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}

}
