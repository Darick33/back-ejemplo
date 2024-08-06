package com.appgym.domain.productos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosListarCategoria(
        @NotNull
        @NotBlank
        UUID id,
        @NotBlank
        String nombre
) {
    public DatosListarCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getNombre());
    }
}
