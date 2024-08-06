package com.appgym.domain.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByEmail(String email);

    @Query("""
        select u from Usuario u where u.rol <> 'SOCIO' and u.superadmin = false
    """)
    Page<Usuario> findUsuariosRolSocio(Pageable pageable);

    @Query("""
        select u from Usuario u where u.rol = 'SOCIO'
    """)
    Page<Usuario> findUsuariosRolOnlySocio(Pageable pageable);
    Usuario getUsuarioById(UUID id);

    @Query("""
        select u from Usuario u where u.rol = 'SOCIO' and u.estado = true
    """)
    Page<Usuario> findUsuariosRolOnlySocioActivo(Pageable pageable);

    @Query("""
        select count(u) from Usuario u where u.rol = 'SOCIO' and u.estado = true
    """)
    int findTotalUsuariosRolOnlySocios();

    @Query("""
        select u from Usuario u where u.rol = 'SOCIO' and u.estado = true
        and u.fechaCreacion between  :fechaInicio and :fechaFin
    """)
    Page<Usuario> findUsuariosRolOnlySocioActivoRangoFecha(
           LocalDateTime fechaInicio,
             LocalDateTime fechaFin,
            Pageable pageable
    );

    Boolean existsUsuarioByEmail(String email);

}
