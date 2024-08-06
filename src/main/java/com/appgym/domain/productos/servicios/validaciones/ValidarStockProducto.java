package com.appgym.domain.productos.servicios.validaciones;

import com.appgym.domain.notasventas.DatosGuardarDetalleNota;
import com.appgym.domain.productos.Producto;
import com.appgym.domain.productos.ProductoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidarStockProducto  implements  ValidacionesProductos {
    @Autowired
    ProductoRepository productoRepository;
    @Override
    public void validar(DatosGuardarDetalleNota datosGuardarDetalleNota) {
        Producto producto = productoRepository.findById(datosGuardarDetalleNota.productoId()).get();
        if(producto.getStock().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("No hay stock");
        }
    }
}
