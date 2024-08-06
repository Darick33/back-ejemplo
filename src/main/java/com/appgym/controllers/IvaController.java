package com.appgym.controllers;

import com.appgym.domain.ivas.DatosRegistrarIva;
import com.appgym.domain.ivas.DatosRespuestaIva;
import com.appgym.domain.ivas.Iva;
import com.appgym.domain.ivas.IvaRepository;
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

@RestController
@RequestMapping("/ivas")
public class IvaController {

    @Autowired
    private IvaRepository ivaRepository;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaIva> registrarIva(@RequestBody @Valid DatosRegistrarIva datosRegistrarIva, UriComponentsBuilder uriComponentsBuilder) {

        Iva iva = ivaRepository.save(new Iva(datosRegistrarIva));

        URI uri = uriComponentsBuilder.path("/ivas/{id}").buildAndExpand(iva.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaIva(iva));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosRespuestaIva>> listarIva(@PageableDefault(page = 0, size = 10, sort = {"fechaRegistro"}) Pageable pageable) {
        return ResponseEntity.ok(ivaRepository.findAll(pageable).map(DatosRespuestaIva::new));
    }

    @Operation(
            summary = "Retorna el ultimo iva ingresado",
            description = "",
            tags = {"iva","membresia"}
    )
    @GetMapping("/ultimo")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaIva> retornarUltimoIva() {
        Iva iva  = ivaRepository.findLastId();

        return ResponseEntity.ok(new DatosRespuestaIva(iva));
    }

}
