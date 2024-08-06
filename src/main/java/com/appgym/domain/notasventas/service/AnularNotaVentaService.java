package com.appgym.domain.notasventas.service;

import com.appgym.domain.notasventas.NotaVenta;
import com.appgym.domain.notasventas.NotaVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnularNotaVentaService {
    @Autowired
    NotaVentaRepository notaVentaRepository;
    @Transactional
    public void anular(UUID id) {
        NotaVenta notaVenta = notaVentaRepository.findById(id).get();
        notaVenta.anular();

        notaVenta.getDetalles().forEach(detalle -> {
            if(detalle.getMembresiaUsuario() != null) {
                detalle.getMembresiaUsuario().anular();
            }
        });
    }
}
