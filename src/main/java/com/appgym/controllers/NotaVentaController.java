package com.appgym.controllers;


import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import com.appgym.domain.notasventas.*;
import com.appgym.domain.notasventas.service.AnularNotaVentaService;
import com.appgym.domain.notasventas.service.GuardarNotaVentaService;
import com.appgym.domain.notasventas.service.NumeroNotaVentaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/notas-ventas")
public class NotaVentaController {

    @Autowired
    NotaVentaRepository notaVentaRepository;
    @Autowired
    NumeroNotaVentaService numeroNotaVentaService;
    @Autowired
    MembresiaUsuarioRepository membresiaUsuarioRepository;
    @Autowired
    GuardarNotaVentaService guardarNotaVentaService;
    @Autowired
    AnularNotaVentaService anularNotaVentaService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaNotaVenta>> listarNotasdeVenta(@PageableDefault(size = Integer.MAX_VALUE, sort = {"numero"}, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(notaVentaRepository.findAll(pageable).map(DatosRespuestaNotaVenta::new));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity<?> guardarNotaVenta(@RequestBody @Valid DatosGuardarNotaVenta datosGuardarNotaVenta, UriComponentsBuilder uriComponentsBuilder) {
        // Hace la persistencia tambiém de membresiaUsuario y los detalles
        NotaVenta notaVenta = guardarNotaVentaService.guardar(datosGuardarNotaVenta);
        URI uri = uriComponentsBuilder.path("/notas-ventas/{id}").buildAndExpand(notaVenta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaNotaVenta(notaVenta));
    }

    @Operation(
            summary = "Devuelve el siguiente número de factura",
            description = "",
            tags = {"nota venta"}
    )
    @GetMapping("/numero")
    public ResponseEntity<DatosRespuestaNumero> numeroNotaVenta() {
        return ResponseEntity.ok(new DatosRespuestaNumero(numeroNotaVentaService.numeroNotaVenta()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity<DatosRespuestaNotaVenta> retornarUnaNotadeVenta(@PathVariable UUID id){
        NotaVenta notaVenta = notaVentaRepository.findById(id).get();
        return ResponseEntity.ok(new DatosRespuestaNotaVenta(notaVenta));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    @Transactional
    public ResponseEntity<?> anularNotaVenta(@PathVariable UUID id) {
        anularNotaVentaService.anular(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalle/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity<DatosRespuestaDetalleNotaVentaUnica> listarDetalleNotaVenta(@PathVariable UUID id){
        NotaVenta notaVenta = notaVentaRepository.findById(id).get();
        System.out.println(notaVenta);
        return ResponseEntity.ok(new DatosRespuestaDetalleNotaVentaUnica(notaVenta));
    } // Pediente ocultar si esta inactiva

    @GetMapping("/socios/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION', 'SOCIO')")
    public ResponseEntity<Page<DatosRespuestaNotaVenta>> listarDetalleSocio(@PathVariable UUID id, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return  ResponseEntity.ok(notaVentaRepository.findNotaVentasByUsuario(id,pageable).map(DatosRespuestaNotaVenta::new));
    }

    @GetMapping("/reporte/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaNotaVenta>> listarReporteNotaventa(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok(notaVentaRepository.findNotaVentaByRangoFecha(fechaInicio,fechaFin,pageable).map(DatosRespuestaNotaVenta::new));
    }
}
