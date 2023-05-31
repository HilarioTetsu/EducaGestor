package com.springboot.educagestor.app.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.springboot.educagestor.app.util.constants.Constants;

public class PerfilAlumnoDTO {

	private String nombreCompleto;
	private String alumnoId;
	private String carrera;
	private String planEstudios;
	private Short generacion;
	private String email;
	@NotBlank(message = Constants.VALIDACION_MENSAJE_NOT_BLANK)
	private String direccion;
	@NotNull(message = Constants.VALIDACION_MENSAJE_NOT_BLANK)
	private Integer codigoPostal;
	@NotNull(message = Constants.VALIDACION_MENSAJE_NOT_BLANK)
	private Long telefono;
	
	public PerfilAlumnoDTO(String nombreCompleto, String alumnoId, String carrera, String planEstudios,
			Short generacion, String email, String direccion, Integer codigoPostal, Long telefono) {
		this.nombreCompleto = nombreCompleto;
		this.alumnoId = alumnoId;
		this.carrera = carrera;
		this.planEstudios = planEstudios;
		this.generacion = generacion;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
	}
	
	

	public PerfilAlumnoDTO() {
	}



	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(String alumnoId) {
		this.alumnoId = alumnoId;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getPlanEstudios() {
		return planEstudios;
	}

	public void setPlanEstudios(String planEstudios) {
		this.planEstudios = planEstudios;
	}

	public Short getGeneracion() {
		return generacion;
	}

	public void setGeneracion(Short generacion) {
		this.generacion = generacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "PerfilAlumnoDTO [nombreCompleto=" + nombreCompleto + ", alumnoId=" + alumnoId + ", carrera=" + carrera
				+ ", planEstudios=" + planEstudios + ", generacion=" + generacion + ", email=" + email + ", direccion="
				+ direccion + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + "]";
	}
	
	
	
	

	
	
}
