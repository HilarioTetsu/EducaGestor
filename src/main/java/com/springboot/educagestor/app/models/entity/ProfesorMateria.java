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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "profesores_materias",indexes = {@Index(columnList = "profesor_id, materia_id,semestre_nombre_id",unique = true)})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorMateria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6972351480976974130L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profesor_materia_id")
	private Integer profesorMateriaId;
	

	@ManyToOne()
	@JoinColumn(name = "profesor_id",referencedColumnName = "profesor_id")
	private Profesor profesor;
	
	
	@ManyToOne()
	@JoinColumn(name = "materia_id",referencedColumnName = "materia_id")
	private Materia materia;
	
	
	
	@ManyToOne()
	@JoinColumn(name = "semestre_nombre_id",referencedColumnName = "semestre_nombre_id")
	private SemestreNombre semestreNombre;
	
	
	@OneToMany(mappedBy = "profesorMateria",fetch = FetchType.LAZY)
	private List<AlumnoMateria> listAlumnoMateria;
	
	@OneToMany(mappedBy = "profesorMateria",fetch = FetchType.LAZY)
	private List<Asistencia> listAsistencia;
	
	@OneToMany(mappedBy = "profesorMateria",fetch = FetchType.LAZY)
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
