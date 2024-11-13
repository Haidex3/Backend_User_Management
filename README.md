# SISTEMA DE GESTIÓN BIBLIOTECARIA

El presente documento detalla las funcionalidades y especificaciones técnicas del Sistema de
Gestión Bibliotecaria que se implementa del Colegio Nuestra Señora de la Sabiduría. Este
sistema se diseña para facilitar la gestión de libros, estudiantes, responsables económicos, y
el registro de préstamos y devoluciones de los libros de la biblioteca del colegio.


## MÓDULO DE GESTIÓN DE ESTUDIANTES Y RESPONSABLES ECONÓMICOS

Este módulo permite la gestión de toda la información de los estudiantes y sus respectivos
responsables económicos. Cada estudiante está vinculado a un responsable económico, quien
será notificado en caso de que un préstamo venza. Cada responsable ecónomico puede
representar a varios estudiantes siempre y cuando estos tengan un parentezco.

#### Datos de los Estudiantes:

- Código del estudiante: Identificación única del estudiante.
- Nombre completo.
- Curso: Ej. Pre-Jardín, Jardín, Primero, etc.
- Año académico: Ej. 2024.
- Datos de los Responsables Económicos:
- Nombre completo.
- Documento de identificación: Cédula o equivalente.
- Relación con el estudiante: Padre, tutor, etc.
- Correo electrónico: Será utilizado para las notificaciones.
- Teléfono de contacto.
- Dirección.
- Cada estudiante puede estar asociado a un solo responsable económico, y este será notificado sobre el estado de los préstamos del estudiante


# MODELO DE ARQUITECTURA

![Arquitectura Microservicios](https://github.com/user-attachments/assets/fc2d3a0f-76d9-448d-84cd-f659bd49184b)



# PERSISTENCIA

Se diseña la base de datos `usermanagement` con los siguientes detalles:


![image](https://github.com/user-attachments/assets/b1678ae2-2b63-4fb7-86c8-5a5813db7ba0)




Posteriormente, el equipo de desarrollo toma la decisión de crear la base de datos tipo Relacional con `Postgres`. 
Esta se despliaga en Azure. 

![image](https://github.com/user-attachments/assets/b896c484-75c9-4492-9485-dbf0f382979a)

Tenienedo en cuenta las siguientes especificaciones:

- Creación de las tablas

```sh
CREATE TABLE Courses (
    id INTEGER,
    name VARCHAR(50)
);

CREATE TABLE Responsibles (
    id VARCHAR(50),
    identification_type VARCHAR(20),
    name VARCHAR(100),
    phoneNumber VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(100)
);

CREATE TABLE Administrators (
    id BIGINT,
    userName VARCHAR(50),
    password VARCHAR(50)
);


CREATE TABLE Students (
    code VARCHAR(50),
    identification_type VARCHAR(20),
    identification_number VARCHAR(50),
    userName INTEGER,
    password INTEGER,
    name VARCHAR(100),
    academicYear INTEGER,
    relationWithResponsible VARCHAR(50),
    idResponsible VARCHAR(50),
    course INTEGER
);
```

- Restricciones

```sh
ALTER TABLE Courses
ADD CONSTRAINT pk_courses PRIMARY KEY (id);

ALTER TABLE Responsibles
ADD CONSTRAINT pk_responsibles PRIMARY KEY (id);

ALTER TABLE Administrators
ADD CONSTRAINT pk_administrators PRIMARY KEY (id);

ALTER TABLE Students
ADD CONSTRAINT pk_students PRIMARY KEY (code);

ALTER TABLE Students
ADD CONSTRAINT uk_students_identification UNIQUE (identification_type, identification_number);

ALTER TABLE Students
ADD CONSTRAINT fk_students_responsible FOREIGN KEY (idResponsible)
REFERENCES Responsibles(id);

ALTER TABLE Students
ADD CONSTRAINT fk_students_course FOREIGN KEY (course)
REFERENCES Courses(id);
```

- Usuario de conexión

```sh
CREATE USER librarydirector WITH PASSWORD 'userManagement2024';
```

- Permisos para el usuario

```sh
GRANT INSERT, UPDATE, DELETE ON TABLE responsibles TO librarydirector;
GRANT INSERT, UPDATE, DELETE ON TABLE administrators TO librarydirector;
GRANT INSERT, UPDATE, DELETE ON TABLE students TO librarydirector;
```

## GIT FLOW

![image](https://github.com/user-attachments/assets/3d7357e6-4f33-4de2-a604-328218f742f4)



