package com.appgym.domain.membresias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.UUID;

public record DatosActualizarMembresia(

        UUID id,

        String nombre,

        String descripcion,

        BigDecimal precio,

        BigDecimal total,

        BigDecimal duracion,

        TipoDuracion tipoDuracion,

        Boolean estado,

        String observaciones
) {
}
