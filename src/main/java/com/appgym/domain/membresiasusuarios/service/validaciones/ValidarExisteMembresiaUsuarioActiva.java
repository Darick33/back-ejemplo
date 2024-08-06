package com.appgym.domain.membresiasusuarios.service.validaciones;

import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import com.appgym.domain.usuarios.Usuario;
import com.appgym.infra.error.DatosRespuestaError;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarExisteMembresiaUsuarioActiva implements ValidacionesMembresiasUsuarios {

    @Autowired
    private MembresiaUsuarioRepository membresiaUsuarioRepository;

    @Override
    public void validar(MembresiaUsuario membresiaUsuario) {
        MembresiaUsuario membresiaUsuarioValidacion = membresiaUsuarioRepository.existsMembresiaUsuarioFaseActivaByUsuarioId(membresiaUsuario.getUsuario().getId());

        if(membresiaUsuarioValidacion != null) {
            throw new ValidationException("Parece que " + membresiaUsuario.getUsuario().getNombre() + " ya tiene una membresía activa");
        }
    }
}
