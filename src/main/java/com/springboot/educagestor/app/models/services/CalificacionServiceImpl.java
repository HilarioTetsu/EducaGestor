package com.springboot.educagestor.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.educagestor.app.models.dao.ICalificacionDao;
import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.entity.Calificacion;

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

	@Override
	@Transactional(readOnly = true)
	public Calificacion findBycalifId(Integer id) {
		// TODO Auto-generated method stub
		return califDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Calificacion findCalificacionByAlumnoIdAndUnidadAndProfesorMateriaId(String alumnoId, Byte unidad,
			Integer profesorMateriaId) {
		// TODO Auto-generated method stub
		return califDao.findCalificacionByAlumnoIdAndUnidadAndProfesorMateriaId(alumnoId, unidad, profesorMateriaId).orElse(null);
	}

	@Override
	@Transactional
	public void save(Calificacion calificacion) {
		
		califDao.save(calificacion);
	}
	
	@Override
	@Transactional
	public void delete(Calificacion calificacion) {
		
		califDao.delete(calificacion);
	}

	@Override
	public Set<Byte> generateSetUnidades(List<CalificacionDTO> lista) {
	
		Set<Byte> unidades=new HashSet<Byte>();
		
		for (CalificacionDTO calif : lista) {
			unidades.add(Byte.parseByte(calif.getUnidad()));
		}
	
		
		return unidades;
	}







}
