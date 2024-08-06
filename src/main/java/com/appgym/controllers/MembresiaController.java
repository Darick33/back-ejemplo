package com.appgym.controllers;

import com.appgym.domain.ivas.Iva;
import com.appgym.domain.ivas.IvaRepository;
import com.appgym.domain.membresias.*;
import com.appgym.domain.socios.DatosListarSocio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping ("/membresias")

public class MembresiaController {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Autowired
    private IvaRepository ivaRepository;

    @Autowired
    private HistorialMembresiaRepository historialMembresiaRepository;

    //TODO: Crear historial Membresia cada que cree una

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaMembresia> registrarMembresia(@RequestBody @Valid DatosGuardarMembresia datosGuardarMembresia, UriComponentsBuilder uriComponentsBuilder){
        Membresia membresia = membresiaRepository.save(new Membresia(datosGuardarMembresia));

        HistorialMembresia historialMembresia = historialMembresiaRepository.save(new HistorialMembresia(membresia));


        URI uri = uriComponentsBuilder.path("/membresias/{id}").buildAndExpand(membresia.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaMembresia(membresia));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaMembresia>> listarMembresias(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(membresiaRepository.findAll(pageable).map(DatosRespuestaMembresia::new));
    }

    //TODO: Datos Actualizados agregar al historial membresia
    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaMembresia> actualizarMembresia(@RequestBody @Valid DatosActualizarMembresia datosActualizarMembresia){
        Membresia membresia = membresiaRepository.findById(datosActualizarMembresia.id()).get();

        membresia.actualizarDatos(datosActualizarMembresia);

        HistorialMembresia historialMembresia = historialMembresiaRepository.save(new HistorialMembresia(membresia));
        return ResponseEntity.ok(new DatosRespuestaMembresia(membresia));

    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<?> eliminarMembresia (@PathVariable UUID id){
        Membresia membresia = membresiaRepository.findById(id).get();
        membresia.cambiarEstado();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaMembresia> retornarDatosMembresia(@PathVariable UUID id){
        Membresia membresia = membresiaRepository.findById(id).get();
        return ResponseEntity.ok(new DatosRespuestaMembresia(membresia));
    }

    @GetMapping("/historial-membresias/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaHistorialMembresia> retornarDatosHistorialMembresia(@PathVariable UUID id){
        HistorialMembresia historialMembresia = historialMembresiaRepository.findLastHistorialMembresiaByIdMembresia(id);
        return ResponseEntity.ok(new DatosRespuestaHistorialMembresia(historialMembresia));
    }

    @GetMapping("/membresias-activas")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaMembresia>> retornarDatosSociosActivos(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(membresiaRepository.findMembresiaActiva(pageable).map(DatosRespuestaMembresia::new));
    }

    @GetMapping("/reporte/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaMembresia>> retornarDatosReporte(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin, @PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(membresiaRepository.findMembresiaReporteByFechaRango(fechaInicio.atTime(0,0,0),fechaFin.atTime(23,59,59),pageable).map(DatosRespuestaMembresia::new));
    }



}
