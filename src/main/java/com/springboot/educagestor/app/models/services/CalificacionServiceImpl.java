package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		List<CalificacionDTO> listResult=new ArrayList<>();
		
		listResult=califDao.findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(acronimo, alumnoId, materiaId);
		
		return listResult;
	}

	@Override
	public Map<String, List<CalificacionDTO>> findCalificacionesSemestreByMateriasAndSemestreAcronimoAndAlumnoId(
			List<String> listMaterias, String acronimo, String alumnoId) {
		
		Map<String, List<CalificacionDTO>> mapResult = new HashMap<>();
		
		
		for (String materia : listMaterias) {
			mapResult.put(materia, findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(acronimo, alumnoId, materia));
		}
		
		
		return mapResult;
	}





}
