package com.appgym.domain.progresosfisicos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRegistrarProgresoFisico(
        LocalDateTime fechaRegistro,
        @NotNull
        BigDecimal peso,
        @NotNull
        BigDecimal altura,
        BigDecimal pecho,
        BigDecimal cintura,
        BigDecimal espalda,
        BigDecimal cadera,
        BigDecimal biceps,
        BigDecimal muslos,
        BigDecimal antebrazos,
        BigDecimal pantorillas,
        String observaciones,
        @NotNull
        UUID usuarioId
) {

}
