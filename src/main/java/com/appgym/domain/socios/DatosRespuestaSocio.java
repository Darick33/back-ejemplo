package com.appgym.domain.socios;

import com.appgym.domain.usuarios.Rol;
import com.appgym.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DatosRespuestaSocio(
        UUID id,
        String nombre,
        String apellido,
        String email,
        String identificacion,
        LocalDate fechaNacimiento,
        String direccion,
        String celular,
        Boolean sexo,
        String observaciones
) {
    public DatosRespuestaSocio(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getIdentificacion(), usuario.getFechaNacimiento(), usuario.getDireccion(), usuario.getCelular(), usuario.getSexo(), usuario.getObservaciones());
    }

}
