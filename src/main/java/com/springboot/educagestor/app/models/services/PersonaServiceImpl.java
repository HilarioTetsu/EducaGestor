package com.springboot.educagestor.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Override
	@Transactional(readOnly = true)
	public Persona findByEmail(String email) {
		// TODO Auto-generated method stub
		return personaDao.findByEmail(email);
	}

	@Override
	public String getFullName(Persona persona) {
		
		StringBuffer sb=new StringBuffer();
		
		sb.append(persona.getNombre());
		sb.append(" ");
		sb.append(persona.getApellidoPaterno());
		sb.append(" ");
		sb.append(persona.getApellidoMaterno());
		
		return sb.toString();
	}

	@Override
	public String getCurrentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
