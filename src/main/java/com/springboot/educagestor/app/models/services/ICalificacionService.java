package com.springboot.educagestor.app.models.services;

import java.util.List;

import com.springboot.educagestor.app.models.dto.CalificacionDTO;

public interface ICalificacionService {

	public List<CalificacionDTO> findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(String acronimo,String alumnoId,String materiaId);
}
