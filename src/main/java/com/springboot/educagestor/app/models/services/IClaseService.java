package com.springboot.educagestor.app.models.services;
import java.util.List;

import com.springboot.educagestor.app.models.entity.Clase;

public interface IClaseService {

	public String[][] getMatrixHorarioByAcronimoSemestreAndAlumnoId(String acronimo,String alumnoId);
	public String[][] inicializarMatriz();
	
}