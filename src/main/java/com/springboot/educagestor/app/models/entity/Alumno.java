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
@Table(name = "alumnos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_sec")
private Integer idSec;

@NotBlank
@Column(length = 11,unique = true,name="alumno_id",nullable = true)
@Size(max = 11)
private String alumnoId;

@NotBlank
@Column(length = 36,name="persona_id")
@Size(max = 36)
private String personaId;

@NotNull
private Short generacion;

@NotNull
@Column(name="semestre_actual")
private Byte semestreActual;

@NotNull
@Column(name="carrera_id")
private Byte carreraId;

@NotNull
@Column(name="creditos_totales")
private Byte creditosTotales;

@NotNull
@Column(name="plan_estudios_id")
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
