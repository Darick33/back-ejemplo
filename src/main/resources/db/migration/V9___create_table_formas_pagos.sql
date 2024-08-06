create table formas_pagos (
    id char(36) not null primary key,
    fecha datetime,
    monto decimal(10,2),
    tipo_pago enum('Efectivo', 'Transferencia'),
    nota_venta_id char(36),
    usuario_id char(36),
    constraint fk_formas_pagos_notas_ventas foreign key (nota_venta_id) references notas_ventas(id),
    constraint fk_formas_pagos_usuarios foreign key (usuario_id) references usuarios(id)
)