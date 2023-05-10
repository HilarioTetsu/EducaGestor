package com.springboot.educagestor.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.ISemestreNombreDao;
import com.springboot.educagestor.app.models.entity.SemestreNombre;

@Service
public class SemestreNombreServiceImpl implements ISemestreNombreService {

	@Autowired
	private ISemestreNombreDao semestreDao;
	



	@Override
	@Transactional(readOnly = true)
	public List<SemestreNombre> findByIds(List<Byte> id) {
		return semestreDao.findAllById(id);
	}




	@Override
	@Transactional(readOnly = true)
	public SemestreNombre findById(Byte id) {
		
		return semestreDao.findById(id).orElse(null);
	}

}
