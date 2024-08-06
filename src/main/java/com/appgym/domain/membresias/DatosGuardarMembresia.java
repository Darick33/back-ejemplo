package com.appgym.domain.membresias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DatosGuardarMembresia(
        @NotBlank
        String nombre,
        String descripcion,
        BigDecimal precio,
        @NotNull
        BigDecimal total,
        @NotNull
        BigDecimal duracion,
        @NotNull
        TipoDuracion tipoDuracion,
        String observaciones
) {
}
