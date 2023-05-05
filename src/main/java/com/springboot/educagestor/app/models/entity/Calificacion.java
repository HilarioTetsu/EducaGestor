package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "calificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calificacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 696143411037093698L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calif_id")
	private Integer califId;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "alumno_materia_id")
	private AlumnoMateria alumnoMateria;
	
	@NotNull
	private Byte calif;
	
	@NotNull
	private Byte unidad;
	
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
