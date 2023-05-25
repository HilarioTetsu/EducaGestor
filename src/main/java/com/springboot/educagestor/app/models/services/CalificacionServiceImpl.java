package com.springboot.educagestor.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.ICalificacionDao;
import com.springboot.educagestor.app.models.dto.CalificacionDTO;

@Service
public class CalificacionServiceImpl implements ICalificacionService {

	@Autowired
	private ICalificacionDao califDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CalificacionDTO> findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(String acronimo,
			String alumnoId, String materiaId) {
		// TODO Auto-generated method stub
		return califDao.findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(acronimo, alumnoId, materiaId);
	}

}
