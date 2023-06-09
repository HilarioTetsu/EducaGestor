package com.springboot.educagestor.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoMateriaTablaDTO {

	private String materiaId;
	
	private String materiaNombre;
	
	private String nombreProfesor;
	
	private Byte semestreNombreId;
	
	private String nombreSemestre;
	
}
