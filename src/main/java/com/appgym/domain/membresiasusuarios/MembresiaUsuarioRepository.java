package com.appgym.domain.membresiasusuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface MembresiaUsuarioRepository extends JpaRepository<MembresiaUsuario, UUID> {

    @Query("""
        select mu from MembresiaUsuario mu where mu.fase = 'Activa'
    """)
    Page<MembresiaUsuario> findMembresiaUsuarioByFaseActiva(Pageable pageable);

    @Query("""
        select mu from MembresiaUsuario mu where mu.fase = 'Caducada'
    """)
    Page<MembresiaUsuario> findMembresiaUsuarioByFaseCaducada(Pageable pageable);

    @Query("""
        select mu from MembresiaUsuario mu where mu.fase = 'Activa' and mu.usuario.id = :usuarioId 
    """)
    MembresiaUsuario findByUsuarioIdActiva(UUID usuarioId);

    @Query("""
        select mu from MembresiaUsuario mu where mu.fechaCreacion between :fechaInicio and :fechaFin
    """)
    Page<MembresiaUsuario> findMembresiaUsuarioReporteByFechaRango(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable);

    @Query("""
            select mu from MembresiaUsuario mu where mu.usuario.id = :usuarioId and mu.fase
            = 'Activa'
            """)
    MembresiaUsuario existsMembresiaUsuarioFaseActivaByUsuarioId(UUID usuarioId);

    @Query("""
        select mu from MembresiaUsuario mu where mu.fase = 'Anulada'
    """)
    Page<MembresiaUsuario> findMembresiaUsuarioByFaseAnulada(Pageable pageable);
}
