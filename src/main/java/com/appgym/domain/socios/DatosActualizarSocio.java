package com.appgym.domain.socios;

import com.appgym.domain.usuarios.Rol;
import com.appgym.domain.usuarios.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DatosActualizarSocio(
        @NotNull
        UUID id,
        String nombre,
        String apellido,

        String email,
        String identificacion,
        LocalDate fechaNacimiento,
        String direccion,
        String celular,
        Boolean estado,
        Rol rol,
        Boolean sexo,
        String observaciones
) {
    public DatosActualizarSocio (Usuario usuario){
        this(usuario.getId(),usuario.getNombre(),usuario.getApellido(),usuario.getEmail(),usuario.getIdentificacion(),usuario.getFechaNacimiento(),usuario.getDireccion(),usuario.getIdentificacion(),usuario.getEstado(),usuario.getRol(), usuario.getSexo(), usuario.getObservaciones());
    }
}
