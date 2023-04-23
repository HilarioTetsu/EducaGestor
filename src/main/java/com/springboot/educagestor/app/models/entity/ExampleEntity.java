package com.springboot.educagestor.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "ejemplo")
public class ExampleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nombre;
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ExampleEntity(Long id, String nombre) {
		
		Id = id;
		this.nombre = nombre;
	}

	public ExampleEntity() {
		
	}
	
	
	
}
