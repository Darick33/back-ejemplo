package com.appgym.domain.socios.service;

import com.appgym.domain.usuarios.Usuario;
import com.appgym.domain.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardarNuevoSocioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario guardar(Usuario usuario) {

        Boolean existeEmail = usuarioRepository.existsUsuarioByEmail(usuario.getEmail());

        if(existeEmail) {
            throw new ValidationException("Ya existe una cuenta con este email.");
        }

        return usuarioRepository.save(usuario);
    }
}
