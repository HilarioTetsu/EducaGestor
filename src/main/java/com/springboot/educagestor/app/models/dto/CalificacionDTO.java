package com.springboot.educagestor.app.models.dto;

public class CalificacionDTO {

	private String unidad;
	private Byte calificacion;
	
	
	public CalificacionDTO(String unidad, Byte calificacion) {		
		this.unidad = unidad;
		this.calificacion = calificacion;
	}


	public CalificacionDTO() {
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public Byte getCalificacion() {
		return calificacion;
	}


	public void setCalificacion(Byte calificacion) {
		this.calificacion = calificacion;
	}


	@Override
	public String toString() {
		return "CalificacionDTO [unidad=" + unidad + ", calificacion=" + calificacion + "]";
	}
	
	
	
	
	
	
}
