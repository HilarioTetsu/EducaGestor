package com.springboot.educagestor.app.models.dto;

import java.util.List;

public class ListaAlumnosProfesorTablaDTO {

	private Integer noLista;
	private String alumnoId;
	private String nombreCompleto;
	private List<CalificacionDTO> calificaciones;
	
	public ListaAlumnosProfesorTablaDTO(String alumnoId, String nombreCompleto) {	
		this.alumnoId = alumnoId;
		this.nombreCompleto = nombreCompleto;
	}


	public ListaAlumnosProfesorTablaDTO() {		
	}


	
	public List<CalificacionDTO> getCalificaciones() {
		return calificaciones;
	}


	public void setCalificaciones(List<CalificacionDTO> calificaciones) {
		this.calificaciones = calificaciones;
	}


	public Integer getNoLista() {
		return noLista;
	}


	public void setNoLista(Integer noLista) {
		this.noLista = noLista;
	}


	public String getAlumnoId() {
		return alumnoId;
	}


	public void setAlumnoId(String alumnoId) {
		this.alumnoId = alumnoId;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	@Override
	public String toString() {
		return "ListaAlumnosProfesorTablaDTO [noLista=" + noLista + ", alumnoId=" + alumnoId + ", nombreCompleto="
				+ nombreCompleto + "]";
	}
	
	
	
}
