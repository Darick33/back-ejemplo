package com.appgym.domain.membresiasusuarios;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosGuardarMembresiaUsuario(
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime fechaInicio,
    String observaciones,
    @NotNull
    Fase fase,
    @NotNull
    UUID historialMembresiaId,
    @NotNull
    UUID usuarioId
) {

}
