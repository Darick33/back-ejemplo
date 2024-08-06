create table membresias (
    id char(36) not null primary key,
    nombre varchar(250),
    descripcion varchar(500),
    precio decimal(10,2),
    iva_id char(36),
    total decimal(10,2),
    duracion decimal(10,2),
    tipo_duracion enum('HOUR', 'DAY', 'MONTH', 'YEAR'),
    fecha_creacion datetime,
    observaciones varchar(700),
    estado TINYINT,
    constraint fk_membresias_ivas foreign key (iva_id) references ivas(id)


)