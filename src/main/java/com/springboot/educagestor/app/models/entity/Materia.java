package com.springboot.educagestor.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "materias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSec;
	
	@NotBlank
	@Column(length = 17,name = "materia_id",nullable = true,unique = true)
	@Size(max = 17)
	private String materiaId;
	
	@NotBlank
	@Column(length = 50)
	@Size(max = 50)
	private String nombre;
	
	@NotNull
	private Byte creditos;
	
	@NotNull
	@Column(name = "academia_id")
	private Byte academiaId;
	
	@NotBlank
	@Column(length = 5)
	@Size(max = 5)
	private String acronimo;
	
	@NotNull
	@Column(name = "planEstudios_id")
	private Byte planEstudiosId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	@NotNull
	private Date fechaCreacion;

	@Column(length = 20,name = "usuario_creacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "fecha_modificacion",nullable = true)
	private Date fechaModificacion;

	@Column(length = 20,nullable = true,name="usuario_modificacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioModificacion;

	@NotNull
	private Short status;
	
	
	
}
