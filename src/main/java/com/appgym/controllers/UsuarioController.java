package com.appgym.controllers;

import com.appgym.domain.usuarios.*;
import com.appgym.domain.usuarios.autenticacion.DatosRespuestaUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity <?> guardarUsuario(@RequestBody @Valid DatosGuardarUsuario datosGuardarUsuario, UriComponentsBuilder uriComponentsBuilder){

        Usuario usuario = usuarioRepository.save(new Usuario(datosGuardarUsuario, passwordEncoder.encode(datosGuardarUsuario.clave())));

        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaUsuario(usuario));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Page<DatosListarUsuario>> listarUsuarios(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(usuarioRepository.findUsuariosRolSocio(pageable).map(DatosListarUsuario::new));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        Usuario usuario = usuarioRepository.findById(datosActualizarUsuario.id()).get();
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }
//
//
    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> eliminarUsuario(@PathVariable UUID id) {
        Usuario usuario  = usuarioRepository.findById(id).get();

        usuario.cambiarEstado();

        return ResponseEntity.noContent().build();
    }
//
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<DatosRespuestaUsuario> retornarDatosUsuario(@PathVariable UUID id) {
        Usuario usuario = usuarioRepository.getUsuarioById(id);
        System.out.println(usuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }


}
