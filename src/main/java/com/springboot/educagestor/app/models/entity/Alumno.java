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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Alumno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8656812121391019579L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sec")
	private Integer idSec;

	@NotBlank
	@Column(length = 11, unique = true, name = "alumno_id", nullable = true)
	@Size(max = 11)
	private String alumnoId;
		

	@OneToOne()
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	@NotNull
	private Short generacion;

	@NotNull
	@Column(name = "semestre_actual")
	private Byte semestreActual;

	
	@ManyToOne()
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;
	

	@NotNull
	@Column(name = "creditos_totales")
	private Byte creditosTotales;


	
	@ManyToOne
	@JoinColumn(name = "plan_estudios_id")
	private PlanEstudios planEstudios;
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "alumno")
	private List<AlumnoMateria> listAlumnoMateria;
	
	

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "alumno")
	private List<Asistencia> asistencia;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	@NotNull
	private Date fechaCreacion;

	@Column(length = 20, name = "usuario_creacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_modificacion", nullable = true)
	private Date fechaModificacion;

	@Column(length = 20, nullable = true, name = "usuario_modificacion")
	@NotBlank
	@Size(max = 20)
	private String usuarioModificacion;

	@NotNull
	private Short status;

}
