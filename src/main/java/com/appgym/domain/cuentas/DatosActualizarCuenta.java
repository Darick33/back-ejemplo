

package com.appgym.domain.cuentas;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record DatosActualizarCuenta(
        UUID id,
        String nombreEmpresa,
        String nombrePropietario,
        String email,
        String celular_1,
        String celular_2,
        String direccion,
        String logoEmpresa,
        String logoPerfil
) {
}

