package com.appgym.controllers;

import com.appgym.domain.productos.*;
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
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<Page<DatosListarCategoria>> listarCategorias(@PageableDefault(size = Integer.MAX_VALUE, sort = {"nombre"}) Pageable pageable){
        return ResponseEntity.ok(categoriaRepository.findAll(pageable).map(DatosListarCategoria::new));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaCategorias> registrarCategoriaProducto(@RequestBody @Valid DatosguardarCategoria datosguardarCategoria, UriComponentsBuilder uriComponentsBuilder){
        Categoria categoria = categoriaRepository.save(new Categoria((datosguardarCategoria)));
        URI uri = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaCategorias(categoria));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaCategorias> actualizarCategoria(@RequestBody @Valid DatosActualizarCategoria datosActualizarCategoria){
        Categoria categoria = categoriaRepository.findById(datosActualizarCategoria.id()).get();
        categoria.actualizarDatosCategoria(datosActualizarCategoria);
        return ResponseEntity.ok(new DatosRespuestaCategorias(categoria));

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','RECEPCION')")
    public ResponseEntity<DatosRespuestaCategorias> retornarDatosCategoria(@PathVariable UUID id){
        Categoria categoria = categoriaRepository.findById(id).get();
        return ResponseEntity.ok(new DatosRespuestaCategorias(categoria));
    }

}
