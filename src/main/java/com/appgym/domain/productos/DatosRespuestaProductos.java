package com.appgym.domain.productos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaProductos(

     UUID id,
     String codigo,
     String nombre,
     String descripcion,
     Categoria categoria,
     BigDecimal precio,
     BigDecimal stock,
     Boolean estado,
     LocalDateTime fechaCreacion
) {

    public DatosRespuestaProductos(Producto producto){
        this(producto.getId(),producto.getCodigo(),producto.getNombre(),producto.getDescripcion(),producto.getCategoria(),producto.getPrecio(),producto.getStock(),producto.getEstado(), producto.getFechaCreacion());
    }
}
