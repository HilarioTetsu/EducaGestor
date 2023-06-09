package com.springboot.educagestor.app.models.dto;

public class ClaseHorariosProfesorMateriasDTO {

	private Byte numDiaSemana;
	private String horario;
	private String aula;
	private String diaSemana;
	
	public ClaseHorariosProfesorMateriasDTO(Byte numDiaSemana, String horario, String aula) {		
		this.numDiaSemana = numDiaSemana;
		this.horario = horario;
		this.aula = aula;
	}

	public ClaseHorariosProfesorMateriasDTO() {		
	}

	public Byte getNumDiaSemana() {
		return numDiaSemana;
	}

	public void setNumDiaSemana(Byte numDiaSemana) {
		this.numDiaSemana = numDiaSemana;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		return "ClaseHorariosDTO [numDiaSemana=" + numDiaSemana + ", horario=" + horario + ", aula=" + aula
				+ ", diaSemana=" + diaSemana + "]";
	}
	
	
	
}
