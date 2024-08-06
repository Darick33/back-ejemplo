package com.appgym.domain.membresias;


import com.appgym.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, UUID> {
    @Query("""
        select m from Membresia m where m.estado = true
    """)
    Page<Membresia> findMembresiaActiva(Pageable pageable);

    @Query("""
        select m from Membresia m where  m.fechaCreacion between  :fechaInicio and :fechaFin
    """)
    Page<Membresia> findMembresiaReporteByFechaRango(LocalDateTime fechaInicio, LocalDateTime fechaFin,Pageable pageable);
}
