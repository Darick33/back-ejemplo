package com.appgym.domain.membresias;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaMembresia(
        UUID id,
        String nombre,
        String descripcion,
        BigDecimal precio,
//        BigDecimal iva,
        BigDecimal total,
        BigDecimal duracion,
        String tipoDuracion,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String observaciones
) {
    public DatosRespuestaMembresia(Membresia membresia){
        this(membresia.getId(),membresia.getNombre(),membresia.getDescripcion(),membresia.getPrecio(),membresia.getTotal(),membresia.getDuracion(), String.valueOf(membresia.getTipoDuracion()),membresia.getFechaCreacion(),membresia.getEstado(),membresia.getObservaciones());
    }
}
