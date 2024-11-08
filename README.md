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


![image](https://github.com/user-attachments/assets/235639cd-edcc-427c-97e2-9126157ab2b2)



Posteriormente, el equipo de desarrollo toma la decisión de crear la base de datos tipo Relacional con `Postgres`. 
Esta se despliaga en Azure. 

![image](https://github.com/user-attachments/assets/b896c484-75c9-4492-9485-dbf0f382979a)

Tenienedo en cuenta las siguientes especificaciones:

- Creación de las tablas

```sh
CREATE TABLE Responsibles (
    id BIGINT PRIMARY KEY, 
    identification_type VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255)
);


CREATE TABLE Students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(50),
    academic_year INT,
    relation_with_responsible VARCHAR(50),
    id_responsible BIGINT
);


CREATE TABLE Administrators (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

- Consideraciones para la autenticación de estudiantes

```sh
ALTER TABLE Students 
ADD COLUMN userName VARCHAR(50) NOT NULL;

ALTER TABLE Students 
ADD COLUMN password VARCHAR(100) NOT NULL;
```


- Restricciones

```sh
ALTER TABLE Students
ADD CONSTRAINT fk_responsible
FOREIGN KEY (id_responsible) REFERENCES Responsibles(id);
```

- Usuario de conexión

```sh
CREATE USER libraryDirector WITH PASSWORD 'userManagement2024';
```

- Permisos para el usuario

```sh
GRANT INSERT, UPDATE, DELETE ON TABLE responsibles TO libraryDirector;
GRANT INSERT, UPDATE, DELETE ON TABLE administrators TO libraryDirector;
GRANT INSERT, UPDATE, DELETE ON TABLE students TO libraryDirector;
```


