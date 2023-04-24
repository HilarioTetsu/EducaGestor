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
	alumno_id VARCHAR(11) NOT NULL UNIQUE
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

/*FKS Profesor*/
ALTER TABLE Profesor ADD CONSTRAINT FOREIGN KEY (persona_id) REFERENCES Persona (persona_id);

ALTER TABLE Profesor ADD CONSTRAINT FOREIGN KEY (academia_id) REFERENCES Academia (academia_id);

CREATE TABLE Administradores (
	admin_id VARCHAR(36) NOT NULL
	,username VARCHAR(6) NOT NULL
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
	,materia_id VARCHAR(17) NOT NULL UNIQUE
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
	horario_id TINYINT NOT NULL auto_increment
	,horario VARCHAR(9) NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (horario_id)
	);

CREATE TABLE Profesor_Materia (
	profesor_id VARCHAR(10) NOT NULL
	,materia_id VARCHAR(17) NOT NULL
	,horario_id TINYINT NOT NULL
	,semestre_nombre_id TINYINT NOT NULL
	,fecha_creacion DATETIME NOT NULL
	,usuario_creacion VARCHAR(20) NOT NULL
	,fecha_modificacion DATETIME NULL
	,usuario_modificacion VARCHAR(20) NULL
	,STATUS TINYINT NOT NULL
	,PRIMARY KEY (
		profesor_id
		,materia_id
		,horario_id
		,semestre_nombre_id
		)
	);

/*FKS Profesor_Materia*/
ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (profesor_id) REFERENCES Profesor (profesor_id);

ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (materia_id) REFERENCES Materia (materia_id);

ALTER TABLE Profesor_Materia ADD CONSTRAINT FOREIGN KEY (horario_id) REFERENCES Horario (horario_id);

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