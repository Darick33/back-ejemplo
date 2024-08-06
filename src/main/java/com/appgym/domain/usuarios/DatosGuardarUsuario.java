package com.appgym.domain.usuarios;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosGuardarUsuario(
        @NotNull
        @Email
        String email,
         @NotNull
         String clave,
         @NotNull
         String nombre,
         @NotNull
         String apellido,
         @NotNull
         Rol rol
) {
}
