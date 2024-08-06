package com.appgym.domain.formapago;

import com.appgym.domain.notasventas.TipoPago;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DatosGuardarFormaPago(
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fecha,
        BigDecimal monto
) {
}
