package com.appgym.domain.notasventas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record DatosRespuestaNotaVenta(
        UUID id,
        String numero,
        String puntoEmision,
        String establecimiento,
        LocalDate fecha,
        TipoPago tipoPago,
        UUID usuarioId,
        String usuarioNombre,
        String usuarioApelldio,
        List<DatosRespuestaDetalleNota> detalles,
        BigDecimal totalNotaVenta,
        Boolean estado,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaNotaVenta(NotaVenta notaVenta) {
        this(notaVenta.getId(),notaVenta.getNumero(), notaVenta.getPuntoEmision(), notaVenta.getEstablecimiento(),
                notaVenta.getFecha(), notaVenta.getTipoPago(),
                notaVenta.getUsuario().getId(),
                notaVenta.getUsuario().getNombre(),
                notaVenta.getUsuario().getApellido(),
                notaVenta.getDetalles().stream().map(DatosRespuestaDetalleNota::new).toList(), notaVenta.getTotal(), notaVenta.getEstado(), notaVenta.getFechaCreacion());
    }
}
