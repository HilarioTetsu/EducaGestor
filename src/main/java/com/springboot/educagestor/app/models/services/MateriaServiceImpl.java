package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<String> findMateriaNombresBySemestreAcronimoAndAlumnoId(String acronimo, String alumnoId) {
		List<String> listResult =new ArrayList<>();
		listResult=materiaDao.findMateriaNombresBySemestreAcronimoAndAlumnoId(acronimo, alumnoId);
		
		return listResult;
	}

}
