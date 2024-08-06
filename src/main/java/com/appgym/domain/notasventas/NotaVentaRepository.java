package com.appgym.domain.notasventas;

import com.appgym.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface NotaVentaRepository extends JpaRepository<NotaVenta, UUID> {
    @Query(
            """
                select N.numero from NotaVenta as N where N.fechaCreacion = (select max(N2.fechaCreacion) from NotaVenta as N2)
            """
    )
    String findLastNumero();

    @Query(
            """
                select N.numero from NotaVenta as N where N.numero = :numero
            """
    )
    NotaVenta findByNumero(String numero);


    @Query("""
        select n from NotaVenta n where n.fecha between :fechaInicio and :fechaFin
    """)
    Page<NotaVenta> findNotaVentaByRangoFecha(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Pageable pageable
    );

    @Query("""
        select n from NotaVenta n where n.usuario.id = :usuarioId
    """)
    Page<NotaVenta> findNotaVentasByUsuario(
            UUID usuarioId,
            Pageable pageable
    );


}
