create table historiales_membresias (
    id char(36) not null primary key,
    fecha_registro datetime not null,
    nombre varchar(250) not null ,
    descripcion varchar(500) not null ,
    precio decimal(10,2) not null ,
    iva_id char(36),
    total decimal(10,2) not null,
    duracion decimal(10,2) not null,
    tipo_duracion enum('HOUR', 'DAY', 'MONTH', 'YEAR'),
    observaciones varchar(700),
    membresia_id char(36),
    constraint fk_historiales_membresias_ivas foreign key (iva_id) references ivas (id),
    constraint fk_historiales_membresias_membresias foreign key (membresia_id) references membresias (id)

)