package com.appgym.controllers;

import com.appgym.domain.cuentas.*;
import com.appgym.domain.usuarios.Usuario;
import com.appgym.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PROPIETARIO')")
    public ResponseEntity<DatosRespuestaCuenta> guardarCuenta(@RequestBody @Valid DatosGuardarCuenta datosGuardarCuenta, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.findById(datosGuardarCuenta.usuarioId()).get();
        Cuenta cuenta = cuentaRepository.save(new Cuenta(datosGuardarCuenta, usuario));

        URI uri = uriComponentsBuilder.path("/cuentas/{id}").buildAndExpand(cuenta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaCuenta(cuenta));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PROPIETARIO')")
    public ResponseEntity<DatosRespuestaCuenta> actualizarCuenta(@RequestBody @Valid DatosActualizarCuenta datosActualizarCuenta) {
        Cuenta cuenta = cuentaRepository.findById(datosActualizarCuenta.id()).get();
        cuenta.acualizar(datosActualizarCuenta);

        return ResponseEntity.ok(new DatosRespuestaCuenta(cuenta));
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PROPIETARIO')")
    public ResponseEntity<DatosRespuestaCuenta> retornarDatosCuentaUsuario(@PathVariable UUID usuarioId){
        Cuenta cuenta = cuentaRepository.findByUsuarioId(usuarioId);

        if(cuenta == null) {
            throw new ValidationException("No tiene una cuenta.");
        }

        return  ResponseEntity.ok(new DatosRespuestaCuenta(cuenta));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PROPIETARIO')")
    public ResponseEntity<DatosRespuestaCuenta> retornarDatosCuenta(@PathVariable UUID id){
        Cuenta cuenta = cuentaRepository.findById(id).get();

        return  ResponseEntity.ok(new DatosRespuestaCuenta(cuenta));
    }
}
