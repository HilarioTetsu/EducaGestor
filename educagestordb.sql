use educagestordb;
/*

Perfil Alumno:

-Crear Horario?
-Revisar Horario (Exportar PDF o excel)
-Revisar Calificaciones
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
   persona_id varchar(36)  NOT NULL,
   nombre varchar(25)  NOT NULL,
   apellido_paterno varchar(20)  NOT NULL,
   apellido_materno varchar(20)  NOT NULL,
   telefono bigint  NOT NULL,
   email varchar(255)  NOT NULL,
   password varchar(60)  NOT NULL,
   direccion varchar(50)  NOT NULL,
   codigo_postal int  NOT NULL,
   fecha_nac date  NOT NULL,
   genero char(1)  NOT NULL,
   fecha_creacion datetime  NOT NULL,
   usuario_creacion varchar(6)  NOT NULL,
   fecha_modificacion datetime  NULL,
   usuario_modificacion varchar(6)  NULL,
   status tinyint  NOT NULL,
   CONSTRAINT Persona_pk PRIMARY KEY (persona_id)
);


CREATE TABLE Alumno (
/*LM(Iniciales de los apellidos)0X(Donde X es el id de la carrera)18(Año de ingreso)Y(Donde Y es un numero secuencial)
Al final quedaria algo asi: LM0518122, este seria el alumno_id*/
   id_sec INT AUTO_INCREMENT PRIMARY KEY,
   alumno_id VARCHAR(11)  NULL,
   persona_id varchar(36)  NOT NULL,
   generacion smallint  NOT NULL,
   semestre_Actual tinyint  NOT NULL,
   carrera_id tinyint  NOT NULL,
   creditos_totales tinyint  NOT NULL,
   planEstudios_id tinyint  NOT NULL
);



CREATE TABLE Profesor (
/*
Formato ID Profesor: PR0XYZS
En donde PR es acronimo,
0X corresponde el id de la academia perteneciente,
YZ ultimos dos digitos del año de ingreso,
S es un numero secuencial
*/
   id_sec INT AUTO_INCREMENT PRIMARY KEY,
   profesor_id varchar(10) NULL,
   persona_id varchar(36)  NOT NULL,
   fecha_ingreso date  NOT NULL,
   academia_id tinyint  NOT NULL
   
);

CREATE TABLE Administradores (
/* tabla administradores*/
   admin_id varchar(36)  NOT NULL,
   username varchar(6)  NOT NULL,
   email varchar(255)  NOT NULL,
   `password` varchar(60)  NOT NULL,
   PRIMARY KEY (admin_id)
);

CREATE TABLE Academia (
    academia_id TINYINT NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY (academia_id)
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
   id_sec smallint AUTO_INCREMENT PRIMARY KEY,
   materia_id varchar(17) NULL,
   nombre varchar(50)  NOT NULL,
   creditos tinyint  NOT NULL,
   academia_id tinyint  NOT NULL,
   acronimo varchar(5)  NOT NULL,
   planEstudios_id tinyint  NOT NULL
);

CREATE TABLE Horario (
   horario_id tinyint  NOT NULL,
   horario varchar(9)  NOT NULL,
   PRIMARY KEY (horario_id)
);

CREATE TABLE Profesor_Materia (
   profesor_id varchar(10)  NOT NULL,
   materia_id varchar(17)  NOT NULL,
   horario_id tinyint  NOT NULL,
   semestre_nombre_id tinyint  NOT NULL,
   PRIMARY KEY (profesor_id, materia_id,horario_id)
);

CREATE TABLE PlanEstudios (
   planEstudios_id tinyint  NOT NULL,
   nombre varchar(30)  NOT NULL,
   descripcion varchar(80)  NOT NULL,
   carrera_id tinyint  NOT NULL,
   PRIMARY KEY (planEstudios_id)
);

CREATE TABLE Semestre_nombre (
   semestre_nombre_id tinyint  NOT NULL,
   semestre varchar(35)  NOT NULL,
   acronimo varchar(25)  NOT NULL,
   PRIMARY KEY (semestre_nombre_id)
);

CREATE TABLE Carrera (
   carrera_id tinyint  NOT NULL,
   descripcion varchar(25)  NOT NULL,
   acronimo varchar(5)  NOT NULL,
   PRIMARY KEY (carrera_id)
);
