package com.appgym.domain.usuarios.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosRegistrarUsuario(
        @Email
        @NotBlank
        @Size(max = 100)
        String email,
        @NotBlank
        String clave,
        @NotNull
        String rol
) {
}
