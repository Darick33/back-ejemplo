package com.appgym.domain.notasventas;

import java.util.List;

public record DatosRespuestaDetalleNotaVentaUnica (
        List<DatosRespuestaDetalleNota> detalles
){

    public DatosRespuestaDetalleNotaVentaUnica(NotaVenta notaVenta){
        this(notaVenta.getDetalles().stream().map(DatosRespuestaDetalleNota::new).toList());
    }
}
