package com.springboot.educagestor.app;






import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.educagestor.app.models.dao.IAlumnoDao;
import com.springboot.educagestor.app.models.dao.ICalificacionDao;
import com.springboot.educagestor.app.models.dao.IClaseDao;
import com.springboot.educagestor.app.models.dao.IMateriaDao;
import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.dto.ClaseHorarioDTO;
import com.springboot.educagestor.app.models.dto.PerfilAlumnoDTO;
import com.springboot.educagestor.app.models.services.ICalificacionService;
import com.springboot.educagestor.app.models.services.IClaseService;



@SpringBootApplication
public class EducaGestorApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IClaseService claseService;
	@Autowired
	private IClaseDao claseDao;
	@Autowired
	private ICalificacionService califService;
	@Autowired
	private IMateriaDao materiaDao;
	@Autowired
	private IAlumnoDao alumnoDao;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EducaGestorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//System.out.println(passwordEncoder.encode("1234"));
		
		/*for (ClaseHorarioDTO item : claseDao.findClaseHorarioBySemestreAcronimoYAlumnoId("ENE20-JUL20", "LG1234")) {
			
			System.out.println(item.getAula());
		}*/
		
		/*String[][] m=claseService.getMatrixHorarioByAcronimoSemestreAndAlumnoId("ENE20-JUL20", "LG1234");
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print("["+m[i][j]+"]");
			}
			System.out.println();
		}*/
		
		/*List<CalificacionDTO> listCalif=califService.findCalificacionesByAcronimoSemestreAndAlumnoIdAndMateriaId("ENE20-JUL20", "LG1234", "FBD-2-3-11");
		
		for (CalificacionDTO item : listCalif) {
			System.out.println(item.getUnidad()+": "+item.getCalificacion());
		}*/
		
		/*for (String string : materiaDao.findMateriaNombresBySemestreAcronimoAndAlumnoId("ENE20-JUL20", "LG1234")) {
			System.out.println(string);
		}*/
		
		/*Map<String, List<CalificacionDTO>> mapResult = new HashMap<>();
		List<String> listCalif=materiaDao.findMateriaNombresBySemestreAcronimoAndAlumnoId("ENE20-JUL20", "LG1234");
		
		mapResult=califService.findCalificacionesSemestreByMateriasAndSemestreAcronimoAndAlumnoId(listCalif,"ENE20-JUL20", "LG1234");
		
		for (Map.Entry mp : mapResult.entrySet()) {
			System.out.println(mp.getKey()+": "+mp.getValue().toString());
		}*/
		
		PerfilAlumnoDTO alumno=alumnoDao.findPerfilAlumnoByAlumnoId("LG1234");
		
		System.out.println(alumno);
		
	}

}
