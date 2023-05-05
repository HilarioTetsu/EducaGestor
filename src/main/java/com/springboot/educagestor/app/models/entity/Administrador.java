package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "administradores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484673061694639356L;

	@Id
	@Column(length = 36, name = "admin_id")
	private String adminId;
	
	@Column(length = 20,nullable = false)
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@Column(length = 255,nullable=false)
	@NotBlank
	@Size(max = 255)
	private String email;
	
	@Column(length = 60,nullable=false)
	@NotBlank
	@Size(max = 60)
	private String password;
	
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
