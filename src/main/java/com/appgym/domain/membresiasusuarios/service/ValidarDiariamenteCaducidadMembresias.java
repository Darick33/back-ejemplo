package com.appgym.domain.membresiasusuarios.service;

import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarDiariamenteCaducidadMembresias {
    @Autowired
    MembresiaUsuarioRepository membresiaUsuarioRepository;

    @Scheduled(cron = "0 0 0 * * *")// Ejecutar todos los días a las 0am
    public void validar() {
        Page<MembresiaUsuario> membresiaUsuarios = membresiaUsuarioRepository.findAll(Pageable.unpaged());

        // Actualizar la fase
        List<MembresiaUsuario> membresiaUsuariosActualizados = membresiaUsuarios
                .stream()
                .map(membresiaUsuario -> {
                    membresiaUsuario.validarCaducidad();
                    return membresiaUsuario;
                })
                .toList();

        // Persistir en todas las membresías
        membresiaUsuarioRepository.saveAll(membresiaUsuariosActualizados);
    }
}
