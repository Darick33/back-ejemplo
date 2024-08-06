package com.appgym.domain.progresosfisicos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosActualizarProgresoFisico(
        UUID id,
        LocalDateTime fechaRegistro,
        BigDecimal peso,
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
        UUID usuario_id
) {

}
