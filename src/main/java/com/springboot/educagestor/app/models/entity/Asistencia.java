package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asistencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6694647887238493383L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asistencia_id")
	private Integer asistenciaId;
	
	@ManyToOne()
	@JoinColumn(name = "alumno_id",referencedColumnName = "alumno_id")
	private Alumno alumno;
	
	@NotNull
	@Column(name = "asistencia_status")
	private Byte asistenciaStatus;
	
	@NotNull
	@Column(name = "fecha_asistencia")
	private LocalDate fechaAsistencia;
	
	
	@ManyToOne()
	@JoinColumn(name="profesor_materia_id",referencedColumnName = "profesor_materia_id")
	private ProfesorMateria profesorMateria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	@NotNull
	private Date fechaCreacion;

	@Column(length = 20,name = "usuario_creacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_modificacion",nullable = true)
	private Date fechaModificacion;

	@Column(length = 20,nullable = true,name="usuario_modificacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioModificacion;

	@NotNull
	private Short status;
	
}
