package com.appgym.domain.productos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosGuardarProducto(
        @NotBlank
        String codigo,
        @NotBlank
        String nombre,
        @NotBlank
        String descripcion,
        @NotNull
        UUID categoria_id,
        @NotNull
        BigDecimal precio,
        @NotNull
        BigDecimal stock,
        LocalDateTime fechaCreacion

) {
}
