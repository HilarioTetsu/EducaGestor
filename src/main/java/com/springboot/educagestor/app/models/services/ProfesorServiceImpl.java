package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IProfesorDao;
import com.springboot.educagestor.app.models.dto.MateriasProfesorTablaDTO;
import com.springboot.educagestor.app.models.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorDao profesorDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public Profesor findByProfesorId(String profesorId) {
		
		return profesorDao.findByProfesorId(profesorId);
	}


	@Override
	@Transactional(readOnly = true)
	public List<MateriasProfesorTablaDTO> findMateriasByProfesorIdAndAcronimoSemestre(String profesorId,
			String acronimo) {
		
		List<MateriasProfesorTablaDTO> listResult= new ArrayList<>();
		
		listResult=profesorDao.findMateriasByProfesorIdAndAcronimoSemestre(profesorId, acronimo);
		
		return listResult;
	}

}
