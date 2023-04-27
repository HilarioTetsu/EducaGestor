package com.springboot.educagestor.app.models.entity;

import java.time.LocalDate;
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
@Table(name = "profesores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sec")
	private Integer idSec;
	
	
	@NotBlank
	@Column(name = "profesor_id",length = 10,nullable = true,unique = true)
	@Size(max = 10)
	private String profesorId;
	
	@NotBlank
	@Column(name = "persona_id",length = 36)
	@Size(max = 36)
	private String personaId;
	
	@NotNull
	@Column(name = "fecha_ingreso")
	private LocalDate fechaIngreso;
	
	@NotNull
	@Column(name = "academia_id")
	private Byte academiaId;
	
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
