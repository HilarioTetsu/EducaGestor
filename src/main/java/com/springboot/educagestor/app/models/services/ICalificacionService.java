package com.springboot.educagestor.app.models.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.entity.Calificacion;

public interface ICalificacionService {

	public List<CalificacionDTO> findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId(String acronimo,String alumnoId,String materiaId);
	public Map<String,List<CalificacionDTO>> findCalificacionesSemestreByMateriasAndSemestreAcronimoAndAlumnoId(List<String> materias,String acronimo,String alumnoId);
	public Calificacion findBycalifId(Integer id);
	public Calificacion findCalificacionByAlumnoIdAndUnidadAndProfesorMateriaId(String alumnoId,Byte unidad, Integer profesorMateriaId);
	public void save(Calificacion calificacion);	
	public Set<Byte> generateSetUnidades(List<CalificacionDTO> lista);
	public void delete(Calificacion calificacion);
	
	
}
