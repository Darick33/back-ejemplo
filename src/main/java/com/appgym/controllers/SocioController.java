package com.appgym.controllers;

import com.appgym.domain.socios.*;
import com.appgym.domain.usuarios.Usuario;
import com.appgym.domain.usuarios.UsuarioRepository;
import com.appgym.domain.usuarios.autenticacion.DatosRespuestaUsuario;
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
@RequestMapping("/socios")



public class SocioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION', 'ENTRENADOR')")
    public ResponseEntity<Page<DatosListarSocio>> listarSocios(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(usuarioRepository.findUsuariosRolOnlySocio(pageable).map(DatosListarSocio::new));

    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaUsuario> registrarSocio(@RequestBody @Valid DatosGuardarSocio datosGuardarSocio, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosGuardarSocio));

        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaUsuario(usuario));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION','SOCIO')")
    public ResponseEntity<DatosRespuestaSocio> actualizarSocio(@RequestBody @Valid DatosActualizarSocio datosActualizarSocio){
        Usuario usuario = usuarioRepository.findById(datosActualizarSocio.id()).get();
        usuario.actualizarDatosSocio(datosActualizarSocio);
        return ResponseEntity.ok(new DatosRespuestaSocio(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<?> eliminarSocio(@PathVariable UUID id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.cambiarEstado();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION','SOCIO')")
    public ResponseEntity<DatosRespuestaSocio> retornarDatosSocio(@PathVariable UUID id){
        Usuario usuario = usuarioRepository.findById(id).get();
        return  ResponseEntity.ok(new DatosRespuestaSocio(usuario));
    }

    @GetMapping("/activos")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarSocio>> retornarDatosSociosActivos(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){

        return ResponseEntity.ok(usuarioRepository.findUsuariosRolOnlySocioActivo(pageable).map(DatosListarSocio::new));
    }

    @GetMapping("/total")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Integer> retornarTotalSocios(){
        Integer total = usuarioRepository.findTotalUsuariosRolOnlySocios();
        return ResponseEntity.ok(total);
    }


    @GetMapping("/reporte/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarSocio>> retornarReporteSociosActivos(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin, @PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){

        return  ResponseEntity.ok( usuarioRepository.findUsuariosRolOnlySocioActivoRangoFecha(fechaInicio.atTime(0,0,0),fechaFin.atTime(23,59,59),pageable).map(DatosListarSocio::new));

    }



}
