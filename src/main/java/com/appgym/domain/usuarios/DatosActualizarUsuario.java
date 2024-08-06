package com.appgym.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosActualizarUsuario(
        @NotNull
        UUID id,
        String email,
        String nombre,
        String apellido,
        Rol rol
) {
}
