package com.appgym.controllers;

import com.appgym.domain.productos.*;
import com.appgym.domain.socios.DatosActualizarSocio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarProducto>> listarProductos(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"})  Pageable pageable){
        return ResponseEntity.ok(productoRepository.findAll(pageable).map(DatosListarProducto::new));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaProductos> registrarProducto(@RequestBody @Valid DatosGuardarProducto datosGuardarProducto, UriComponentsBuilder uriComponentsBuilder){
        Categoria categoria = categoriaRepository.findById(datosGuardarProducto.categoria_id()).get();
        Producto producto = productoRepository.save(new Producto(datosGuardarProducto, categoria));
        URI uri = uriComponentsBuilder.path("/productos/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaProductos(producto));

    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaProductos> actualizarProducto(@RequestBody @Valid DatosActualizarProducto datosActualizarProducto){
        Producto producto = productoRepository.findById(datosActualizarProducto.id()).get();
        Categoria categoria = categoriaRepository.findById(datosActualizarProducto.categoria_id()).get();
        producto.actualizarDatosProducto(datosActualizarProducto, categoria);
        return ResponseEntity.ok(new DatosRespuestaProductos(producto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<?> eliminarProducto(@PathVariable UUID id){
        Producto producto = productoRepository.findById(id).get();
        producto.cambiarEstado();
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaProductos> retornarDatosProducto(@PathVariable UUID id){
        Producto producto = productoRepository.findById(id).get();
        return  ResponseEntity.ok(new DatosRespuestaProductos(producto));
    }

    @GetMapping("/activos")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Integer> retornarDatosProductosActivosCantidad(){
        return ResponseEntity.ok(productoRepository.findOnlyProductosActivos());
    }

    @GetMapping("/activos-total")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarProducto>> retornarDatosProductosActivos(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(productoRepository.findProductoOnlyActivo(pageable).map(DatosListarProducto ::new));
    }

    @GetMapping("/reportes/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarProducto>> retornarReporteProductos(@PageableDefault  (size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable, @PathVariable LocalDate fechaInicio,@PathVariable LocalDate fechaFin){
        return ResponseEntity.ok(productoRepository.findProductoReporteByFechas(fechaInicio.atTime(0,0,0),fechaFin.atTime(23,59,59),pageable).map(DatosListarProducto::new));
    }

    
}
