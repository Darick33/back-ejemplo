package com.appgym.controllers;


import com.appgym.domain.membresias.DatosRespuestaMembresia;
import com.appgym.domain.membresias.Membresia;
import com.appgym.domain.membresiasusuarios.DatosGuardarMembresiaUsuario;
import com.appgym.domain.membresiasusuarios.DatosRespuestaMembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.membresiasusuarios.MembresiaUsuarioRepository;
import com.appgym.domain.membresiasusuarios.service.ListarMembresiasUsuariosValidandoCaducidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/membresias-usuarios")
public class MembresiasUsuarioController {
    @Autowired
    private MembresiaUsuarioRepository membresiaUsuarioRepository;
    @Autowired
    private ListarMembresiasUsuariosValidandoCaducidadService listarMembresiasUsuariosValidandoCaducidadService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity <Page<DatosRespuestaMembresiaUsuario>> listarMembresiasUsuarios(@PageableDefault(size = Integer.MAX_VALUE, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(listarMembresiasUsuariosValidandoCaducidadService.listar(pageable).map(DatosRespuestaMembresiaUsuario::new));
    }

    @GetMapping("/activas")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity <Page<DatosRespuestaMembresiaUsuario>> listarMembresiasUsuariosActivas(@PageableDefault(size = Integer.MAX_VALUE, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(membresiaUsuarioRepository.findMembresiaUsuarioByFaseActiva(pageable).map(DatosRespuestaMembresiaUsuario::new));
    }

    @GetMapping("/caducadas")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity <Page<DatosRespuestaMembresiaUsuario>> listarMembresiasUsuariosCaducadas(@PageableDefault(size = Integer.MAX_VALUE, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(membresiaUsuarioRepository.findMembresiaUsuarioByFaseCaducada(pageable).map(DatosRespuestaMembresiaUsuario::new));
    }
    @GetMapping("/anuladas")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity <Page<DatosRespuestaMembresiaUsuario>> listarMembresiasUsuariosAnuladas(@PageableDefault(size = Integer.MAX_VALUE, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(membresiaUsuarioRepository.findMembresiaUsuarioByFaseAnulada(pageable).map(DatosRespuestaMembresiaUsuario::new));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RECEPCION')")
    public ResponseEntity <DatosRespuestaMembresiaUsuario> retornarDatosMembresiasUsuarios(@PathVariable UUID id){
        MembresiaUsuario membresiaUsuario = membresiaUsuarioRepository.findById(id).get();
        return ResponseEntity.ok(new DatosRespuestaMembresiaUsuario(membresiaUsuario));
    }

    @GetMapping("activa/{usuarioId}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION','ENTRENADOR','SOCIO')")
    public ResponseEntity<DatosRespuestaMembresiaUsuario> retornarDatosMembresiaUsuarioActiva(@PathVariable UUID usuarioId){
        MembresiaUsuario membresiaUsuario = membresiaUsuarioRepository.findByUsuarioIdActiva(usuarioId);
        return ResponseEntity.ok(new DatosRespuestaMembresiaUsuario(membresiaUsuario));
    }

    @GetMapping("reporte/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION','ENTRENADOR','SOCIO')")
    public ResponseEntity<Page<DatosRespuestaMembresiaUsuario>> retornarDatosMembresiaUsuaioReporte(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin, Pageable pageable){
        return ResponseEntity.ok(membresiaUsuarioRepository.findMembresiaUsuarioReporteByFechaRango(fechaInicio.atTime(0,0,0),fechaFin.atTime(23,59,59),pageable).map(DatosRespuestaMembresiaUsuario::new));
    }

}
