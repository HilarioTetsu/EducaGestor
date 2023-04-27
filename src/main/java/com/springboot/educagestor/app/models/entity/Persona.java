package com.springboot.educagestor.app.models.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

	@Id
	@Column(name = "persona_id",length = 36)
	private String personaId;
	
	@Column(length = 25,nullable = false)
	@NotBlank
	@Size(max = 25)
	private String nombre;
	
	@Column(length = 20,nullable = false)
	@NotBlank
	@Size(max = 20)
	private String apellidoPaterno;
	
	@Column(length = 20,nullable = false)
	@NotBlank
	@Size(max = 20)
	private String apellidoMaterno;
	
	@NotNull
	@Column(nullable = false)
	private Long telefono;
	
	@Column(length = 255,nullable = false)
	@NotBlank
	@Size(max = 255)
	@Email
	private String email;
	
	@Column(length = 60,nullable = false)
	@NotBlank
	@Size(max = 60)
	private String password;
	
	@Column(length = 50,nullable = false)
	@NotBlank
	@Size(max = 50)
	private String direccion;
	
	@NotNull
	@Column(nullable = false)
	private Integer codigoPostal;
	
	@Past
	@NotNull
	private LocalDate fechaNac;
	
	@Column(length = 1,nullable = false)
	@NotBlank
	@Size(max = 1)
	private String genero;
	
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
