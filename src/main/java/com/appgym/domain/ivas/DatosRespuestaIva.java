package com.appgym.domain.ivas;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaIva(
        UUID id,
        BigDecimal iva,
        LocalDateTime fechaRegistro
) {

    public DatosRespuestaIva(Iva iva) {
        this(iva.getId(), iva.getIva(), iva
                .getFechaRegistro());
    }
}
