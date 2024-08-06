create table productos (
    id char(36) not null primary key,
    codigo VARCHAR(255),
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    precio decimal(10,2),
    stock decimal(10,2),
    categoria_id char(36),
    constraint fk_categorias_productos foreign key (categoria_id) references categorias(id),
    estado tinyint default 1

)