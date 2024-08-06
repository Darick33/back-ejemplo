
package com.appgym.domain.cuentas;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosGuardarCuenta(
        String nombreEmpresa,
        String nombrePropietario,
        String email,
        String celular_1,
        String celular_2,
        String direccion,
        String logoEmpresa,
        String logoPerfil,
        @NotNull
        UUID usuarioId
) {
}

