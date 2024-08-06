package com.appgym.domain.notasventas.service;

import com.appgym.domain.membresias.HistorialMembresia;
import com.appgym.domain.membresias.HistorialMembresiaRepository;
import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import com.appgym.domain.membresiasusuarios.service.validaciones.ValidacionesMembresiasUsuarios;
import com.appgym.domain.membresiasusuarios.service.validaciones.ValidarExisteMembresiaUsuarioActiva;
import com.appgym.domain.notasventas.DatosGuardarNotaVenta;
import com.appgym.domain.notasventas.DetalleNota;
import com.appgym.domain.notasventas.NotaVenta;
import com.appgym.domain.notasventas.NotaVentaRepository;
import com.appgym.domain.productos.Producto;
import com.appgym.domain.productos.ProductoRepository;
import com.appgym.domain.productos.servicios.validaciones.ValidacionesProductos;
import com.appgym.domain.usuarios.Usuario;
import com.appgym.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GuardarNotaVentaService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MembresiaUsuarioRepository membresiaUsuarioRepository;
    @Autowired
    HistorialMembresiaRepository historialMembresiaRepository;
    @Autowired
    NotaVentaRepository notaVentaRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    List<ValidacionesMembresiasUsuarios> validacionesMembresiasUsuarios;
    @Autowired
    List<ValidacionesProductos> validacionesProductos;
    public NotaVenta guardar(DatosGuardarNotaVenta datosGuardarNotaVenta) {
        Usuario usuario = usuarioRepository.getUsuarioById(datosGuardarNotaVenta.usuarioId());

        NotaVenta notaVenta = new NotaVenta(datosGuardarNotaVenta, usuario);

        datosGuardarNotaVenta.detalles().forEach(detalle -> {
            // Detalle para membresía
            if(detalle.membresiaUsuario() != null) {
                Usuario usuarioMembresia = usuarioRepository.getUsuarioById(detalle.membresiaUsuario().usuarioId()); // Si tienen membresia si no ver si tiene producto
                HistorialMembresia historialMembresia = historialMembresiaRepository.findById(detalle.membresiaUsuario().historialMembresiaId()).get(); // igual
                MembresiaUsuario membresiaUsuario = new MembresiaUsuario(detalle.membresiaUsuario(), historialMembresia, usuarioMembresia); // Igual
                // Validaciones
                validacionesMembresiasUsuarios.forEach(validacion -> validacion.validar(membresiaUsuario)); // igual
                notaVenta.agregarDetalle(new DetalleNota(detalle, membresiaUsuario));
            // Detalle para productos
            } else {
                Producto producto = productoRepository.findById(detalle.productoId()).get();
                // Actualizar el stock
                producto.actualizarStock(new BigDecimal(detalle.cantidad()));
                // Validaciones
                validacionesProductos.forEach(validacion -> validacion.validar(detalle));
                notaVenta.agregarDetalle(new DetalleNota(detalle, producto));
            }

        });
//        return notaVenta;
        return notaVentaRepository.save(notaVenta);
    }
}
