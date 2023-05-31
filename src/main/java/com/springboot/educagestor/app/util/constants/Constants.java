package com.springboot.educagestor.app.util.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants {

	public static final String ROLE_ALUMNO = "ROLE_ALUMNO";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String VALIDACION_MENSAJE_NOT_BLANK = "El campo no debe estar vacio";
	public static final String VALIDACION_MENSAJE_No_TELEFONICO = "Debe ser un numero telefonico de 10 digitos";
	public static final String VALIDACION_MENSAJE_CPOSTAL = "Debe ser un codigo postal de 5 digitos";
	public static final Byte DIA_LUNES=1;
	public static final Byte DIA_MARTES=2;
	public static final Byte DIA_MIERCOLES=3;
	public static final Byte DIA_JUEVES=4;
	public static final Byte DIA_VIERNES=5;
	public static final Byte DIA_SABADO=6;
	public static final String[] CABECERA_TABLA_CLASEHORARIO= {"X","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};
	public static final Map<String, Integer> HORARIOS_MAP;
	public static Set<Integer> DIA_SEMANA_SET;
	
	static {
		HORARIOS_MAP = new HashMap<>();
		HORARIOS_MAP.put("7:00-7:55", 1);
		HORARIOS_MAP.put("7:55-8:50", 2);
		HORARIOS_MAP.put("8:50-9:45", 3);
		HORARIOS_MAP.put("9:45-10:40", 4);
		HORARIOS_MAP.put("10:40-11:35", 5);
		HORARIOS_MAP.put("11:35-12:30", 6);
		HORARIOS_MAP.put("12:30-13:25", 7);
		HORARIOS_MAP.put("13:25-14:20", 8);
        HORARIOS_MAP.put("14:20-15:15", 9);
        HORARIOS_MAP.put("15:15-16:10", 10);
        HORARIOS_MAP.put("16:10-17:05", 11);
        HORARIOS_MAP.put("17:05-18:00", 12);
        HORARIOS_MAP.put("18:00-18:55", 13);
        HORARIOS_MAP.put("18:55-19:50", 14);
        HORARIOS_MAP.put("19:50-20:45", 15);
        
        DIA_SEMANA_SET = new HashSet<>();
        DIA_SEMANA_SET.addAll(Arrays.asList(
                Integer.valueOf(DIA_LUNES),
                Integer.valueOf(DIA_MARTES),
                Integer.valueOf(DIA_MIERCOLES),
                Integer.valueOf(DIA_JUEVES),
                Integer.valueOf(DIA_VIERNES)
        ));
    }
	
}
