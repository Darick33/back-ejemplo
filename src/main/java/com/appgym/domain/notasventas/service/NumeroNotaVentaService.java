package com.appgym.domain.notasventas.service;

import com.appgym.domain.notasventas.NotaVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumeroNotaVentaService {
    @Autowired
    private NotaVentaRepository notaVentaRepository;

    public String numeroNotaVenta() {
        String numero = notaVentaRepository.findLastNumero();
        // En caso de que sea la primera nota ve venta inicia como 0
        if(numero == null) {
            numero = "000000000";
        }

        // Iterar el numero
        String ceros = "";
        Integer num = Integer.parseInt(numero);
        numero = String.valueOf(num + 1);
        num = 9 - numero.length();

        for(int i = 0; i < num; i++) {
            ceros += "0";
        }

        return ceros + numero;
    }


}

