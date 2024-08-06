package com.appgym.domain.membresiasusuarios;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaMembresiaUsuario(
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin,
    String observaciones,
    Fase fase,
    UUID historialMembresiaId,
    String nombreMembresia,
    String descripcionMembresia,
    String nombreUsuario,
    String apellidoUsuario,
    String identificacionUsuario,
    LocalDateTime fechaCreacion,
    UUID usuarioId
) {
    public DatosRespuestaMembresiaUsuario(MembresiaUsuario membresiaUsuario) {
        this(membresiaUsuario.getFechaInicio(), membresiaUsuario.getFechaFin(),
                membresiaUsuario.getObservaciones(), membresiaUsuario.getFase(),
                membresiaUsuario.getHistorialMembresia().getId(),membresiaUsuario.getHistorialMembresia().getNombre(),membresiaUsuario.getHistorialMembresia().getDescripcion(),membresiaUsuario.getUsuario().getNombre(), membresiaUsuario.getUsuario().getApellido(),membresiaUsuario.getUsuario().getIdentificacion(),membresiaUsuario.getFechaCreacion(),membresiaUsuario.getUsuario().getId());
    }
}
