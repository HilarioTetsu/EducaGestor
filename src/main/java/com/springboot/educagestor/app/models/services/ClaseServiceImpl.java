package com.springboot.educagestor.app.models.services;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.springboot.educagestor.app.models.dao.IClaseDao;
import com.springboot.educagestor.app.models.dto.ClaseHorarioDTO;

import com.springboot.educagestor.app.util.constants.Constants;

@Service
public class ClaseServiceImpl implements IClaseService {

	@Autowired
	private IClaseDao claseDao;

	private final Logger logger = LoggerFactory.getLogger(ClaseServiceImpl.class);

	// private com.springboot.educagestor.app.models.dto.ClaseHorarioDTO dto = new
	// com.springboot.educagestor.app.models.dto.ClaseHorarioDTO();

	@Override
	@Transactional(readOnly = true)
	public String[][] getMatrixHorarioByAcronimoSemestreAndAlumnoId(String acronimo, String alumnoId) {
		String[][] matrixResult = inicializarMatriz();

		List<ClaseHorarioDTO> listClaseHorario = new ArrayList<ClaseHorarioDTO>();
		listClaseHorario = claseDao.findClaseHorarioBySemestreAcronimoYAlumnoId(acronimo, alumnoId);
		listClaseHorario.sort(Comparator.comparing(ClaseHorarioDTO::getDiaSemana));

		


	
		
		
		for (ClaseHorarioDTO item : listClaseHorario) {
			
			/*logger.info("Materia: "+item.getNombre());
			logger.info("Aula: "+item.getAula());
			logger.info("dia: "+item.getDiaSemana());
			logger.info("horario: "+item.getHorario());
			logger.info("**********************************************************");*/
			
			
			if (Constants.HORARIOS_MAP.containsKey(item.getHorario()) && Constants.DIA_SEMANA_SET.contains(item.getDiaSemana().intValue())) {
				
				matrixResult[Constants.HORARIOS_MAP.get(item.getHorario())][item.getDiaSemana()]=item.getNombre()+"\n"+item.getAula();
			}
			
		}
		
		
		
		
		return matrixResult;
	}

	@Override
	public String[][] inicializarMatriz() {
		String[][] matrixResult = new String[16][6];
		matrixResult[0] = Constants.CABECERA_TABLA_CLASEHORARIO;

		LocalTime hora_inicio = LocalTime.of(07, 00);
		LocalTime hora_final = LocalTime.of(07, 55);

		for (int i = 1; i < matrixResult.length; i++) {
			for (int j = 0; j < matrixResult[i].length; j++) {

				if (j == 0) {

					matrixResult[i][j] = hora_inicio.toString().concat("-").concat(hora_final.toString());
					hora_inicio = hora_inicio.plusMinutes(55);
					hora_final = hora_final.plusMinutes(55);
					continue;
				}

				matrixResult[i][j] = "";

			}
		}

		return matrixResult;
	}

}
