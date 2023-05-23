package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Set;

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




	@Override
	@Transactional(readOnly = true)
	public SemestreNombre findByAcronimo(String acronimo) {
		// TODO Auto-generated method stub
		return semestreDao.findByAcronimo(acronimo);
	}




	@Override
	@Transactional(readOnly = true)
	public Set<String> findSemestresByAlumnoId(String alumnoId) {
		// TODO Auto-generated method stub
		return semestreDao.findSemestresByAlumnoId(alumnoId);
	}

}
