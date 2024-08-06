package com.appgym.domain.membresiasusuarios.service;

import com.appgym.domain.membresiasusuarios.DatosRespuestaMembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarMembresiasUsuariosValidandoCaducidadService {
    @Autowired
    MembresiaUsuarioRepository membresiaUsuarioRepository;

    public Page<MembresiaUsuario> listar(Pageable pageable) {
        Page<MembresiaUsuario> membresiaUsuarios = membresiaUsuarioRepository.findAll(pageable);

        // Actualizar la fase
        List<MembresiaUsuario> membresiaUsuariosActualizados = membresiaUsuarios
                .stream()
                .map(membresiaUsuario -> {
                    membresiaUsuario.validarCaducidad();
                    return membresiaUsuario;
                })
                .toList();


        membresiaUsuarioRepository.saveAll(membresiaUsuariosActualizados);

        return membresiaUsuarioRepository.findAll(pageable);
    }

}
