package com.appgym.domain.notasventas;

import com.appgym.domain.membresiasusuarios.DatosGuardarMembresiaUsuario;
import com.appgym.domain.productos.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosGuardarDetalleNota(
        @NotBlank
        String cantidad,
        @NotBlank
        String precio,
        @NotBlank
        String subtotal,
        @NotBlank
        String total,
        DatosGuardarMembresiaUsuario membresiaUsuario,
        UUID productoId,
        @NotBlank
        String iva
) {
}
