ALTER TABLE detalles_notas
    ADD producto_id char(36),
    ADD CONSTRAINT fk_detalles_notas_producto FOREIGN KEY (producto_id) REFERENCES productos(id);