create table usuarios (
    id char(36) not null primary key,
    email varchar(100) not null unique,
    clave varchar(100) not null,
    nombre varchar(50),
    apellido varchar(50),
    rol enum('ADMINISTRADOR','ENTRENADOR','RECEPCION','SOCIO') not null,
    identificacion varchar(15),
    fecha_nacimiento date,
    sexo tinyint,
    direccion varchar(255),
    celular varchar(20),
    observaciones varchar(255),
    fecha_creacion datetime default current_timestamp,
    estado tinyint default 1
)