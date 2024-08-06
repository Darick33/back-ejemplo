
package com.appgym.domain.cuentas;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record DatosRespuestaCuenta(
        UUID id,
        String nombreEmpresa,
        String nombrePropietario,
        String email,
        String celular_1,
        String celular_2,
        String direccion,
        String logoEmpresa,
        String logoPerfil,
        UUID usuarioId
) {
    public DatosRespuestaCuenta(Cuenta cuenta) {
        this(cuenta.getId(), cuenta.getNombreEmpresa(), cuenta.getNombrePropietario(), cuenta.getEmail(), cuenta.getCelular_1(), cuenta.getCelular_2(), cuenta.getDireccion(), cuenta.getLogoEmpresa(), cuenta.getLogoPerfil(), cuenta.getUsuario().getId());
    }
}

