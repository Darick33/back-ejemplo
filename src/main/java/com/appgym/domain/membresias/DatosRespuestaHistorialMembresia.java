package com.appgym.domain.membresias;

import com.appgym.domain.membresias.HistorialMembresia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaHistorialMembresia(
        UUID id,
        String nombre,
        String descripcion,
        BigDecimal precio,
//        BigDecimal iva,
        BigDecimal total,
        BigDecimal duracion,
        String tipoDuracion,
        LocalDateTime fechaRegistro,
        String observaciones
) {
    public DatosRespuestaHistorialMembresia(HistorialMembresia historialMembresia){
        this(historialMembresia.getId(),historialMembresia.getNombre(),historialMembresia.getDescripcion(),historialMembresia.getPrecio(),historialMembresia.getTotal(),historialMembresia.getDuracion(), String.valueOf(historialMembresia.getTipoDuracion()),historialMembresia.getFechaRegistro(),historialMembresia.getObservaciones());
    }
}
