create table ivas (
    id char(36) not null primary key,
    iva decimal(10,2) not null,
    fecha_registro datetime not null
)