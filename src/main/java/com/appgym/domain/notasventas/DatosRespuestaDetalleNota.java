package com.appgym.domain.notasventas;

import com.appgym.domain.membresiasusuarios.DatosGuardarMembresiaUsuario;
import com.appgym.domain.membresiasusuarios.DatosRespuestaMembresiaUsuario;
import com.appgym.domain.productos.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosRespuestaDetalleNota(
        BigDecimal cantidad,
        BigDecimal precio,
        BigDecimal subtotal,
        BigDecimal total,
        DatosRespuestaMembresiaUsuario datosRespuestaMembresiaUsuario,
        Producto producto,
        BigDecimal iva
) {
        public DatosRespuestaDetalleNota(DetalleNota detalleNota) {
                this(detalleNota.getCantidad(), detalleNota.getPrecio(),
                        detalleNota.getSubtotal(), detalleNota.getTotal(),
                        (detalleNota.getMembresiaUsuario() != null ? new DatosRespuestaMembresiaUsuario(detalleNota.getMembresiaUsuario()) : null),
                        (detalleNota.getProducto() != null ? detalleNota.getProducto() : null),
                        detalleNota.getIva());
        }

}
