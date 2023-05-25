package com.springboot.educagestor.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IMateriaDao;
import com.springboot.educagestor.app.models.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaDao materiaDao;
	
	@Override
	@Transactional
	public Materia findByMateriaId(String materiaId) {
		// TODO Auto-generated method stub
		return materiaDao.findByMateriaId(materiaId).orElse(null);
	}

}
