package com.springboot.educagestor.app.models.services;



import com.springboot.educagestor.app.models.entity.AlumnoMateria;

public interface IAlumnoMateriaService {

	public AlumnoMateria findByalumnoIdAndprofesorMateriaId(String alumnoId,Integer profesorMateriaId);
}
