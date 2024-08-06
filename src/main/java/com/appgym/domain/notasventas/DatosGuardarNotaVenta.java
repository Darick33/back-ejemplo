package com.appgym.domain.notasventas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DatosGuardarNotaVenta(
        @NotBlank
        String numero,
        @NotBlank
        String puntoEmision,
        @NotBlank
        String establecimiento,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha,
        @NotNull
        TipoPago tipoPago,
        @NotNull
        UUID usuarioId,
        @NotNull
        List<DatosGuardarDetalleNota> detalles,
        @NotNull
        BigDecimal total
) {
}
