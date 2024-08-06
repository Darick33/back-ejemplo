package com.appgym.domain.progresosfisicos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaProgresoFisico(
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
        public DatosRespuestaProgresoFisico(ProgresoFisico progresoFisico) {
                this(progresoFisico.getId(),
                        progresoFisico.getFechaRegistro(),
                        progresoFisico.getPeso(),
                        progresoFisico.getAltura(),
                        progresoFisico.getPecho(),
                        progresoFisico.getCintura(),
                        progresoFisico.getEspalda(),
                        progresoFisico.getCadera(),
                        progresoFisico.getBiceps(),
                        progresoFisico.getMuslos(),
                        progresoFisico.getAntebrazos(),
                        progresoFisico.getPantorillas(),
                        progresoFisico.getObservaciones(),
                        progresoFisico.getUsuario().getId()
                );
        }
}
