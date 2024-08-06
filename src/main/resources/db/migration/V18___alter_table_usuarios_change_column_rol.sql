alter table usuarios
    modify rol enum('ADMINISTRADOR','ENTRENADOR','RECEPCION','SOCIO','PROPIETARIO') not null;