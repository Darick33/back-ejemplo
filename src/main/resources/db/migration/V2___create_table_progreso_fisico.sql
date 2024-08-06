create table progresos_fisicos (
    id char(36) not null primary key,
    fecha_registro datetime not null,
    peso decimal(10,2),
    altura decimal(10,2),
    hombro decimal(10,2),
    cuello decimal(10,2),
    pecho decimal(10,2),
    cintura decimal(10,2),
    cadera decimal(10,2),
    bicep_derecho decimal(10,2),
    bicep_izquierdo decimal(10,2),
    muslo_derecho decimal(10,2),
    muslo_izquierdo decimal(10,2),
    antebrazo_derecho decimal(10,2),
    antebrazo_izquierdo decimal(10,2),
    pantorilla_derecha decimal(10,2),
    pantorilla_izquierda decimal(10,2),
    observaciones varchar(500),
    usuario_id char(36),

    constraint fk_progresos_fisicos_usuario foreign key (usuario_id) references usuarios (id)
)