package com.appgym.domain.socios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosGuardarSocio(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @Email @NotBlank
        String email,
        @NotBlank
        String clave,
        @NotBlank
        String identificacion,
        @NotNull
        LocalDate fechaNacimiento,
        @NotNull
        Boolean sexo,
        @NotBlank
        String direccion,
        @NotBlank
        String celular,
        @NotBlank
        String observaciones

) {

}
