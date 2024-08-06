package com.appgym.domain.progresosfisicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProgresoFisicoRepository extends JpaRepository<ProgresoFisico, UUID> {
    Page<ProgresoFisico> findALLByUsuarioId(Pageable pageable, UUID usuarioId);

    @Query("""
        SELECT p FROM ProgresoFisico p where p.fechaRegistro = (SELECT max(p2.fechaRegistro) from ProgresoFisico p2 where p2.usuario.id = :usuarioId)
    """)
    ProgresoFisico findLastByUsuarioId(UUID usuarioId);

    @Query("""
        SELECT p FROM ProgresoFisico p WHERE p.usuario.id = :usuarioId and p.fechaRegistro between :fechaInicio and :fechaFin ORDER BY p.fechaRegistro DESC
    """)
    Page<ProgresoFisico> findProgresoFisicoByUsuarioId(LocalDateTime fechaInicio, LocalDateTime fechaFin, UUID usuarioId, Pageable pageable);

}
