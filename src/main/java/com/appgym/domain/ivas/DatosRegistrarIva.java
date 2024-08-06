package com.appgym.domain.ivas;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosRegistrarIva(
        @NotNull
        BigDecimal iva
) {
}
