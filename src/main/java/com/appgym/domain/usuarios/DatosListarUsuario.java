package com.appgym.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosListarUsuario(
        UUID id,
        @NotNull
        @Email
        String email,
        @NotNull
        String nombre,
        @NotNull
        String apellido,
        @NotNull
        Rol rol,
        @NotNull
        Boolean estado
) {
    public DatosListarUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getEmail(),usuario.getNombre(),usuario.getApellido(),usuario.getRol(), usuario.getEstado());
    }
}
