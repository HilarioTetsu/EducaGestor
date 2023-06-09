package com.springboot.educagestor.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriasProfesorTablaDTO {

	private String materiaId;
	private String materiaNombre;
	private Integer profesorMateriaId;
	
}
