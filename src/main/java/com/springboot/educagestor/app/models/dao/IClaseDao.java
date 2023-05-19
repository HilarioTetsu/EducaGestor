package com.springboot.educagestor.app.models.dao;

import java.util.List;





import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.educagestor.app.models.dto.ClaseHorarioDTO;
import com.springboot.educagestor.app.models.entity.Clase;





public interface IClaseDao extends CrudRepository<Clase, Integer>{

	@Query("SELECT NEW com.springboot.educagestor.app.models.dto.ClaseHorarioDTO(pm.materia.nombre,lc.aula,lc.horario.horario,lc.horario.diaSemana) from AlumnoMateria am join  am.profesorMateria pm join  pm.listClase lc join  pm.semestreNombre sm where sm.acronimo=?1 and am.alumno.alumnoId=?2")
    List<ClaseHorarioDTO> findClaseHorarioBySemestreAcronimoYAlumnoId(String acronimo,String alumnoId);
}
