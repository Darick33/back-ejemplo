create table cuentas(
    id char(36) not null primary key,
    nombre_empresa varchar(100),
    nombre_propietario varchar(100),
    email varchar(50),
    celular_1 varchar(20),
    celular_2 varchar(20),
    direccion varchar(500),
    logo_empresa longblob,
    logo_perfil longblob,
    usuario_id char(36),

    constraint fk_cuentas_usuario_id foreign key (usuario_id) references usuarios(id)
)

