package com.springboot.educagestor.app.models.dto;

public class ListaAlumnosProfesorTablaDTO {

	private Integer noLista;
	private String alumnoId;
	private String nombreCompleto;
	
	
	public ListaAlumnosProfesorTablaDTO(String alumnoId, String nombreCompleto) {	
		this.alumnoId = alumnoId;
		this.nombreCompleto = nombreCompleto;
	}


	public ListaAlumnosProfesorTablaDTO() {		
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
