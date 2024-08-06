create table notas_ventas (
    id char(36) not null primary key,
    numero varchar(20),
    punto_emision varchar(10),
    establecimiento varchar(10),
    fecha date,
    fecha_creacion datetime,
    tipo_pago enum('Efectivo','Transferencia'),
    usuario_id char(36),
    total decimal(10,2),
    constraint fk_notas_ventas_usuarios foreign key (usuario_id) references usuarios(id)
)