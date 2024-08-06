package com.appgym.domain.productos;

import java.util.UUID;

public record DatosRespuestaCategorias(
        UUID id,
        String nombre
) {
    public DatosRespuestaCategorias(Categoria categoria){
        this(categoria.getId(),categoria.getNombre());
    }
}
