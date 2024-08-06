package com.appgym.domain.productos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosguardarCategoria(
        @NotBlank
        String nombre
) {
}
