create table detalles_notas (
    id char(36) not null primary key,
    cantidad decimal(10,2),
    precio decimal(10,2),
    subtotal decimal(10,2),
    total decimal(10,2),
    membresia_usuario_id char(36),
    iva decimal(10,2),
    nota_venta_id char(36),
    constraint fk_detalles_notas_membresia_usuario foreign key (membresia_usuario_id) references membresias_usuarios(id),
    constraint fk_detalles_notas_notas_ventas foreign key (nota_venta_id) references notas_ventas(id)
)