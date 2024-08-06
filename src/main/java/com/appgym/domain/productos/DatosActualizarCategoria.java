package com.appgym.domain.productos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosActualizarCategoria(
        @NotNull
        UUID id,
        String nombre
) {
}
