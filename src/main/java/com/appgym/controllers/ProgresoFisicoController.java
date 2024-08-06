package com.appgym.controllers;

import com.appgym.domain.progresosfisicos.*;
import com.appgym.domain.usuarios.Usuario;
import com.appgym.domain.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.UUID;

@RestController
@RequestMapping("/progresos-fisicos")
public class ProgresoFisicoController {

    @Autowired
    private ProgresoFisicoRepository progresoFisicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<DatosRespuestaProgresoFisico> registrarProgresoFisico(@RequestBody @Valid DatosRegistrarProgresoFisico datosRegistrarProgresoFisico, UriComponentsBuilder uriComponentsBuilder) {
        // Usuario
        Usuario usuario = usuarioRepository.findById(datosRegistrarProgresoFisico.usuarioId()).get();

        ProgresoFisico progresoFisico = progresoFisicoRepository.save(new ProgresoFisico(datosRegistrarProgresoFisico, usuario));

        URI uri = uriComponentsBuilder.path("/progresos-fisicos/{id}").buildAndExpand(progresoFisico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaProgresoFisico(progresoFisico));
    }

    @Operation(
            summary = "Lista los progresos fisicos del socio",
            description = "Tiene que pasar como header el parametro usuarioId de cual va a listar",
            tags = {"progreso fisico","socio"}
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<Page<DatosRespuestaProgresoFisico>> listarProgresoFisico(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, @RequestHeader("usuarioId") UUID usuarioId) {
        return ResponseEntity.ok(progresoFisicoRepository.findALLByUsuarioId(pageable, usuarioId).map(DatosRespuestaProgresoFisico::new));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<DatosRespuestaProgresoFisico> actualizarProgresoFisico(@RequestBody @Valid DatosActualizarProgresoFisico datosActualizarProgresoFisico){
        ProgresoFisico progresoFisico = progresoFisicoRepository.findById(datosActualizarProgresoFisico.id()).get();
        progresoFisico.actualizarDatos(datosActualizarProgresoFisico);
        return ResponseEntity.ok(new DatosRespuestaProgresoFisico(progresoFisico));
    }

    @Operation(
            summary = "Elimina permanentemente el progreso fisico",
            description = "",
            tags = {"progreso fisico","socio"}
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<?> eliminarProgresoFisico(@PathVariable UUID id) {
        ProgresoFisico progresoFisico  = progresoFisicoRepository.findById(id).get();

        progresoFisicoRepository.delete(progresoFisico);

        return ResponseEntity.noContent().build();
    }
    //
    @GetMapping("/{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<DatosRespuestaProgresoFisico> retornarDatosProgresoFisico(@PathVariable UUID id) {
        ProgresoFisico progresoFisico  = progresoFisicoRepository.findById(id).get();

        return ResponseEntity.ok(new DatosRespuestaProgresoFisico(progresoFisico));
    }

    @GetMapping("/last/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")

    public ResponseEntity<DatosRespuestaProgresoFisico> retornarDatosUltimoProgresoFisico(@PathVariable UUID id){
        ProgresoFisico progresoFisico = progresoFisicoRepository.findLastByUsuarioId(id);
        return ResponseEntity.ok(new DatosRespuestaProgresoFisico(progresoFisico));
    }

    @GetMapping("/reporte/{id}/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENTRENADOR','SOCIO')")
    public ResponseEntity<Page<DatosRespuestaProgresoFisico>> retornarDatosReporteFisicoUsuario(@PathVariable UUID id, @PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok(progresoFisicoRepository.findProgresoFisicoByUsuarioId(fechaInicio.atTime(0,0,0), fechaFin.atTime(23,59,59),id,pageable).map(DatosRespuestaProgresoFisico::new));

    }

}
