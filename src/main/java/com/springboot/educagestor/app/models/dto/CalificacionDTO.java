package com.springboot.educagestor.app.models.dto;

public class CalificacionDTO {

	private String unidad;
	private Byte calificacion;
	private Byte unidadNum;
	
	
	public CalificacionDTO(String unidad, Byte calificacion) {		
		this.unidad = unidad;
		this.calificacion = calificacion;
		this.unidadNum=Byte.parseByte(unidad);
	}


	public CalificacionDTO() {
	}


	
	public CalificacionDTO(String unidad) {
		this.unidad = unidad;
		this.unidadNum=Byte.parseByte(unidad);
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

	
	public Byte getUnidadNum() {
		return unidadNum;
	}


	public void setUnidadNum(Byte unidadNum) {
		this.unidadNum = unidadNum;
	}


	@Override
	public String toString() {
		return "CalificacionDTO [unidad=" + unidad + ", calificacion=" + calificacion + ", unidadNum=" + unidadNum
				+ "]";
	}



	
	
	
	
	
}
