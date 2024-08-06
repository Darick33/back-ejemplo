create table membresias_usuarios (
    id char(36) not null primary key,
    fecha_inicio datetime,
    fecha_fin datetime,
    fecha_creacion datetime,
    observaciones varchar(500),
    fase enum('Activa', 'Caducada', 'Pendiente'),
    historial_membresia_id char(36),
    usuario_id char(36),
    estado tinyint,

    constraint fk_membresias_usuarios_historial_membresias foreign key (historial_membresia_id) references historiales_membresias(id),
    constraint fK_membresias_usuarios_usuario foreign key (usuario_id) references usuarios(id)
)