package com.appgym.domain.productos;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosActualizarProducto(
        @NotNull
        UUID id,

        String codigo,

        String nombre,

        String descripcion,

        UUID categoria_id,

        BigDecimal precio,

        BigDecimal stock,
        LocalDateTime fechaCreacion
) {
}
