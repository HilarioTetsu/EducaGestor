package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "horarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3086850381853493582L;

	@Id
	@Column(name = "horario_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte horarioId;
	
	@Column(name = "dia_semana")
	@NotNull
	private Byte diaSemana;
	
	@NotBlank
	@Column(length = 9)
	@Size(max = 9)
	private String horario;
	
	
	@OneToMany(mappedBy = "horario",fetch = FetchType.LAZY)
	private List<Clase> listClase;
	
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
