package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Map;

import com.springboot.educagestor.app.models.dto.CalificacionDTO;

public interface ICalificacionService {

	public List<CalificacionDTO> findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(String acronimo,String alumnoId,String materiaId);
	public Map<String,List<CalificacionDTO>> findCalificacionesSemestreByMateriasAndSemestreAcronimoAndAlumnoId(List<String> materias,String acronimo,String alumnoId);
}
