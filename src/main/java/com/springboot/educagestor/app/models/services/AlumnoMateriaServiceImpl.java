package com.springboot.educagestor.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IAlumnoMateriaDao;
import com.springboot.educagestor.app.models.entity.AlumnoMateria;

@Service
public class AlumnoMateriaServiceImpl implements IAlumnoMateriaService {

	@Autowired
	private IAlumnoMateriaDao alumnoMateriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public AlumnoMateria findByalumnoIdAndprofesorMateriaId(String alumnoId, Integer profesorMateriaId) {
		// TODO Auto-generated method stub
		return alumnoMateriaDao.findByalumnoIdAndprofesorMateriaId(alumnoId, profesorMateriaId).orElse(null);
	}

}
