package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "clases",indexes = {@Index(columnList = "horario_id, profesor_materia_id",unique = true)})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2948806064463771272L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clase_id")
	private Integer claseId;
	
	
	@ManyToOne()
	@JoinColumn(name = "profesor_materia_id",referencedColumnName = "profesor_materia_id")
	private ProfesorMateria profesorMateria;
	
	
	
	@ManyToOne()
	@JoinColumn(name="horario_id",referencedColumnName = "horario_id")
	private Horario horario;
	
	
	@NotBlank
	@Column(name = "aula",length = 5)
	@Size(max = 5)
	private String aula;
	
	
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
