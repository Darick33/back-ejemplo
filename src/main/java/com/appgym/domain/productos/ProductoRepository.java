package com.appgym.domain.productos;


import com.appgym.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    @Query("""
        select count(p) from Producto p where p.estado = true
    """)
    int findOnlyProductosActivos();

    @Query("""
    SELECT  p FROM Producto p where p.estado = true
    """)
    Page<Producto> findProductoOnlyActivo(Pageable pageable);

    @Query("""
        select p from Producto p where p.estado = true
        and p.fechaCreacion between  :fechaInicio and :fechaFin
    """)
    Page<Producto> findProductoReporteByFechas(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable
    );

}
