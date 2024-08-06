package com.appgym.domain.usuarios.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @Email
        String email,
        @NotBlank
        String clave) {
}
