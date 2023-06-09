package com.springboot.educagestor.app.models.dto;








public class ClaseHorarioDTO {

	private String nombre;
	private String aula;
	private String horario;
	private Byte diaSemana;
	
	
	
	
	
	public ClaseHorarioDTO() {
	}
	
	public ClaseHorarioDTO(String nombre, String aula, String horario, Byte diaSemana) {
		super();
		this.nombre = nombre;
		this.aula = aula;
		this.horario = horario;
		this.diaSemana = diaSemana;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Byte getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(Byte diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		return "ClaseHorarioDTO [nombre=" + nombre + ", aula=" + aula + ", horario=" + horario + ", diaSemana="
				+ diaSemana + "]";
	}
	
	
	
	
	
	
	
	
}
