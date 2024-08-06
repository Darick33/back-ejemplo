package com.appgym.domain.socios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosRegistrarSocio(
        @Email
        @NotBlank
        @Size(max = 100)
        String email,
        @NotBlank
        String clave,
        @NotBlank
        String nombre,
        @NotBlank
        String apellido
) {
}
