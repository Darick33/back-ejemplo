ALTER TABLE notas_ventas
    ADD estado tinyint;

ALTER TABLE membresias_usuarios
    MODIFY COLUMN fase enum('Activa', 'Caducada', 'Pendiente', 'Anulada');
