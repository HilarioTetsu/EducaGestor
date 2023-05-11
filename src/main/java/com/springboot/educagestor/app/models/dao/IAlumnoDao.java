package com.springboot.educagestor.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.educagestor.app.models.entity.Alumno;


public interface IAlumnoDao extends JpaRepository<Alumno, String>{

}
