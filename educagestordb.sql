USE educagestordb;

/*

Perfil Alumno:

-Crear Horario?
-Revisar Horario (Exportar PDF o excel)
-Revisar Calificaciones/Historial de cada materia
-Pagar Matricula? 

Perfil Profesor:

-Revisar Materias Asignadas
-Ver alumnos por clase/materia
-Subir asistencia diaria
-Subir calificaciones

Perfil Admin

-Acceso total a la información
-Alta, actualización y eliminación de información
-Asignacion de materias a profesores

Perfil Usuario General:
-Login
-Sign Up?

Campos Auditoria:

Todas las tablas llevaran sus campos de auditoria, 
nomas las coloque en persona porque me dio hueva ponerlas en las demas,
pero imagina que estan ahi xd
Actualizacion: deberia colocarlos en este script pero me sigue dando hueva,
 mañana se hace xd
*/
CREATE TABLE Persona (
	persona_id VARCHAR(36) NOT NULL
	,nombre VARCHAR(25) NOT NULL
	,apellido_paterno VARCHAR(20) NOT NULL
	,apellido_materno VARCHAR(20) NOT NULL
	,telefono BIGINT NOT NULL
	,email VARCHAR(255) NOT NULL
	,password VARCHAR(60) NOT NULL
	,direccion VARCHAR(50) NOT NULL
	,codigo_postal INT NOT NULL
	,fecha_nac DATE NOT NULL
	,genero CHAR(1) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (persona_id)
	);

CREATE TABLE Alumno (
	/*LM(Iniciales de los apellidos)0X(Donde X es el id de la carrera)18(Año de ingreso)Y(Donde Y es un numero secuencial)
Al final quedaria algo asi: LM0518122, este seria el alumno_id*/
	alumno_id VARCHAR(11)  NULL UNIQUE
	,id_sec INT NOT NULL auto_increment
	,persona_id VARCHAR(36) NOT NULL
	,generacion SMALLINT NOT NULL
	,semestre_Actual TINYINT NOT NULL
	,carrera_id TINYINT NOT NULL
	,creditos_totales TINYINT NOT NULL
	,planEstudios_id TINYINT NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (id_sec)
	);

/*FKS Alumno*/
ALTER TABLE Alumno ADD CONSTRAINT FOREIGN KEY (persona_id) REFERENCES Persona (persona_id);

ALTER TABLE Alumno ADD CONSTRAINT FOREIGN KEY (carrera_id) REFERENCES Carrera (carrera_id);

ALTER TABLE Alumno ADD CONSTRAINT FOREIGN KEY (planEstudios_id) REFERENCES PlanEstudios (planEstudios_id);

CREATE TABLE Profesor (
	/*
Formato ID Profesor: PR0XYZS
En donde PR es acronimo,
0X corresponde el id de la academia perteneciente,
YZ ultimos dos digitos del año de ingreso,
S es un numero secuencial
*/
	id_sec INT AUTO_INCREMENT
	,profesor_id VARCHAR(10) NOT NULL UNIQUE
	,persona_id VARCHAR(36) NOT NULL
	,fecha_ingreso DATE NOT NULL
	,academia_id TINYINT NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (id_sec)
	);
    
ALTER TABLE Profesor MODIFY COLUMN profesor_id VARCHAR(10) NULL UNIQUE;
/*FKS Profesor*/
ALTER TABLE Profesor ADD CONSTRAINT FOREIGN KEY (persona_id) REFERENCES Persona (persona_id);

ALTER TABLE Profesor ADD CONSTRAINT FOREIGN KEY (academia_id) REFERENCES Academia (academia_id);

CREATE TABLE Administradores (
	admin_id VARCHAR(36) NOT NULL
	,username VARCHAR(20) NOT NULL
	,email VARCHAR(255) NOT NULL
	,`password` VARCHAR(60) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (admin_id)
	);

CREATE TABLE Academia (
	academia_id TINYINT NOT NULL auto_increment
	,descripcion VARCHAR(50) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (academia_id)
	);

CREATE TABLE Materia (
	/*
Formato materia_Id : A-BC-CD-S
A=Acronimo materia
BC=Creditos materia 2 digitos
CD=Id Academia
S= numero secuencial 
Ejemplo: QUI-04-08-42
*/
	id_sec SMALLINT AUTO_INCREMENT
	,materia_id VARCHAR(17)  NULL UNIQUE
	,nombre VARCHAR(50) NOT NULL
	,creditos TINYINT NOT NULL
	,academia_id TINYINT NOT NULL
	,acronimo VARCHAR(5) NOT NULL
	,planEstudios_id TINYINT NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (id_sec)
	);

/*FKS Materia*/
ALTER TABLE Materia ADD CONSTRAINT FOREIGN KEY (academia_id) REFERENCES Academia (academia_id);

ALTER TABLE Materia ADD CONSTRAINT FOREIGN KEY (planEstudios_id) REFERENCES PlanEstudios (planEstudios_id);

CREATE TABLE Horario (
  `horario_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `dia_semana` tinyint(4) NOT NULL,
  `horario` varchar(9) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `usuario_creacion` varchar(20) NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_modificacion` varchar(20) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL,
   PRIMARY KEY (horario_id)
	);
    CREATE TABLE clase(
    clase_id INT NOT NULL AUTO_INCREMENT,
    materia_id VARCHAR(17) NOT NULL,
    horario_id tinyint(4) NOT NULL,
    aula VARCHAR(5),
    fecha_creacion DATETIME NOT NULL,
	usuario_creacion VARCHAR(20) NOT NULL,
	fecha_modificacion DATETIME NULL,
	usuario_modificacion VARCHAR(20) NULL,
	STATUS TINYINT NOT NULL,
    PRIMARY KEY (clase_id),
    CONSTRAINT FOREIGN KEY (materia_id) REFERENCES Materia (materia_id),
    CONSTRAINT FOREIGN KEY (horario_id) REFERENCES Horario (horario_id)
    );
 
 CREATE TABLE asistencia(
 asistencia_id INT NOT NULL AUTO_INCREMENT,
 alumno_id VARCHAR(11) NOT NULL,
 asistencia_status TINYINT NOT NULL,
 fecha_asistencia DATE NOT NULL,
 profesor_materia_id INT,
 fecha_creacion DATETIME NOT NULL,
 usuario_creacion VARCHAR(20) NOT NULL,
 fecha_modificacion DATETIME NULL,
 usuario_modificacion VARCHAR(20) NULL,
 STATUS TINYINT NOT NULL,
 PRIMARY KEY (asistencia_id),
 CONSTRAINT FOREIGN KEY (alumno_id) REFERENCES alumno (alumno_id),
 CONSTRAINT FOREIGN KEY (profesor_materia_id) REFERENCES Profesor_Materia (profesor_materia_id)
 );

CREATE TABLE Profesor_Materia (
	profesor_materia_id INT auto_increment 
	,profesor_id VARCHAR(10) NOT NULL UNIQUE
	,materia_id VARCHAR(17) NOT NULL UNIQUE
	,semestre_nombre_id TINYINT NOT NULL UNIQUE
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (profesor_materia_id)
	);

/*FKS Profesor_Materia*/
ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (profesor_id) REFERENCES Profesor (profesor_id);

ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (materia_id) REFERENCES Materia (materia_id);

ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (semestre_nombre_id) REFERENCES Semestre_nombre (semestre_nombre_id);

CREATE TABLE PlanEstudios (
	planEstudios_id TINYINT NOT NULL auto_increment
	,nombre VARCHAR(30) NOT NULL
	,descripcion VARCHAR(80) NOT NULL
	,carrera_id TINYINT NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (planEstudios_id)
	);
    /*FKS PlanEstudios*/
    ALTER TABLE PlanEstudios 
    ADD CONSTRAINT 
    FOREIGN KEY (carrera_id) 
    REFERENCES Carrera (carrera_id);


CREATE TABLE Semestre_nombre (
	semestre_nombre_id TINYINT NOT NULL auto_increment
	,semestre VARCHAR(35) NOT NULL
	,acronimo VARCHAR(25) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (semestre_nombre_id)
	);

CREATE TABLE Carrera (
	carrera_id TINYINT NOT NULL auto_increment
	,descripcion VARCHAR(25) NOT NULL
	,acronimo VARCHAR(5) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (carrera_id)
	);
    
CREATE TABLE alumno_materia(
id INT NOT NULL AUTO_INCREMENT,
alumno_id VARCHAR(11) NOT NULL,
profesor_materia_id INT NOT NULL,
fecha_creacion DATETIME NOT NULL,
usuario_creacion VARCHAR(20) NOT NULL,
fecha_modificacion DATETIME NULL,
usuario_modificacion VARCHAR(20) NULL,
STATUS TINYINT NOT NULL,
PRIMARY KEY (id),
CONSTRAINT FOREIGN KEY (alumno_id) REFERENCES alumno (alumno_id),
CONSTRAINT FOREIGN KEY (profesor_materia_id) REFERENCES Profesor_Materia (profesor_materia_id)
);

CREATE TABLE calificacion(
calif_id INT NOT NULL AUTO_INCREMENT,
alumno_materia_id INT NOT NULL,
calif TINYINT NOT NULL,
unidad TINYINT NOT NULL,
fecha_creacion DATETIME NOT NULL,
usuario_creacion VARCHAR(20) NOT NULL,
fecha_modificacion DATETIME NULL,
usuario_modificacion VARCHAR(20) NULL,
STATUS TINYINT NOT NULL,
PRIMARY KEY (calif_id),
CONSTRAINT FOREIGN KEY (alumno_materia_id) REFERENCES alumno_materia (id)
);

DELIMITER //
CREATE PROCEDURE asignacionIdAlumnos()
BEGIN
    UPDATE alumnos AS a
    SET a.alumno_id = (
        SELECT CONCAT(LEFT(b.apellido_paterno, 1), LEFT(b.apellido_materno, 1), a.carrera_id, RIGHT(a.generacion, 2), a.id_sec)
        FROM personas AS b
        WHERE a.persona_id = b.persona_id
    );
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE asignacionIdProfesor()
BEGIN
     UPDATE profesores AS a
    SET a.profesor_id = (
        SELECT CONCAT('PR', a.academia_id, RIGHT(YEAR(a.fecha_ingreso), 2), a.id_sec)
        FROM personas AS b
        WHERE a.persona_id = b.persona_id
    );
END //
DELIMITER ;

 UPDATE profesores AS a
    SET a.profesor_id = (
        SELECT CONCAT('PR', a.academia_id, RIGHT(YEAR(a.fecha_ingreso), 2), a.id_sec)
        FROM personas AS b
        WHERE a.persona_id = b.persona_id
    );
    
    SELECT CONCAT('PR', a.academia_id, RIGHT(YEAR(a.fecha_ingreso), 2), a.id_sec)
        FROM profesores AS a
        INNER JOIN personas as b
        WHERE a.persona_id = b.persona_id;
        
DELIMITER //
CREATE PROCEDURE asignacionIdMateria()
BEGIN
    UPDATE materias AS a
    SET a.materia_id = (
        SELECT CONCAT(b.acronimo, '-',b.creditos, '-',b.academia_id,'-', b.id_sec)
        FROM materias AS b
        WHERE a.id_sec = b.id_sec
    ) WHERE a.id_sec>=6;
END //
DELIMITER ;        
    
