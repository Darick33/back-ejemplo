package com.appgym.domain.productos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosListarProducto(
        @NotNull
        @NotBlank
        UUID id,
        @NotBlank
        String codigo,
        @NotBlank
        String nombre,
        @NotBlank
        String descripcion,
        String categoria,
        @NotNull
        BigDecimal precio,
        @NotNull
        BigDecimal stock,
        Boolean estado,
        LocalDateTime fechaCreacion


) {

    public DatosListarProducto(Producto producto){
        this(producto.getId(), producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria().getNombre(),producto.getPrecio(), producto.getStock(), producto.getEstado(), producto.getFechaCreacion());
    }
}
