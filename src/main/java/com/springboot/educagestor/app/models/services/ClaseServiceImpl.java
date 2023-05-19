package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.IClaseDao;
import com.springboot.educagestor.app.models.entity.Clase;

@Service
public class ClaseServiceImpl implements IClaseService {

	/*@Autowired
	private IClaseDao claseDao;*/
	
	//private com.springboot.educagestor.app.models.dto.ClaseHorarioDTO dto = new com.springboot.educagestor.app.models.dto.ClaseHorarioDTO();

	@Override
	@Transactional(readOnly = true)
	public String[][] getMatrixHorarioByAcronimoSemestreAndAlumnoId(String acronimo, String alumnoId) {
		String[][] matrixResult= new String[16][6];
		
		
		
		
		return null;
	}
	


}
