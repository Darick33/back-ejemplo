package com.appgym.domain.socios;

import com.appgym.domain.usuarios.Rol;
import com.appgym.domain.usuarios.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DatosListarSocio(
        @NotNull
        @NotBlank
        UUID id,
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @Email @NotBlank
        String email,
        @NotBlank
        String identificacion,
        @NotNull
        LocalDate fechaNacimiento,
        @NotBlank
        String direccion,
        @NotBlank
        String celular,
        Boolean estado,
        Rol rol


) {
    public DatosListarSocio (Usuario usuario){
        this(usuario.getId(),usuario.getNombre(),usuario.getApellido(),usuario.getEmail(),usuario.getIdentificacion(),usuario.getFechaNacimiento(),usuario.getDireccion(),usuario.getIdentificacion(),usuario.getEstado(),usuario.getRol());
    }
}
