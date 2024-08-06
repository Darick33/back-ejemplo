
package com.appgym.domain.cuentas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
    @Query("""
            select c from Cuenta c where c.usuario.id = :usuarioId
            """)
    Cuenta findByUsuarioId(UUID usuarioId);
}
