package com.appgym.domain.usuarios.autenticacion;

import com.appgym.domain.usuarios.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public record DatosRespuestaUsuario(
        UUID id,
        String nombre,
        String apellido,
        String email,
        Collection<? extends GrantedAuthority> rol

) {
        public DatosRespuestaUsuario(Usuario usuario) {
                this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getAuthorities());
        }
}
