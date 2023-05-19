package com.springboot.educagestor.app;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.educagestor.app.models.dao.IClaseDao;
import com.springboot.educagestor.app.models.dao.IClaseDao2;
import com.springboot.educagestor.app.models.dto.ClaseHorarioDTO;
import com.springboot.educagestor.app.models.entity.Clase;



@SpringBootApplication
public class EducaGestorApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IClaseDao2 claseDao;
	

	
	public static void main(String[] args) {
		SpringApplication.run(EducaGestorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(passwordEncoder.encode("1234"));
		
		for (ClaseHorarioDTO item : claseDao.findClaseHorarioBySemestreAcronimoYAlumnoId("ENE20-JUL20", "LG1234")) {
			
			System.out.println(item.getAula());
		}

	}

}
