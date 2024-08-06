package com.appgym.domain.membresias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistorialMembresiaRepository extends JpaRepository<HistorialMembresia, UUID> {
    @Query(
            """
                select h from HistorialMembresia h where h.id = (select max(h2.id) from HistorialMembresia h2 where h2.membresia.id = :membresiaId)
            """
    )
    HistorialMembresia findLastHistorialMembresiaByIdMembresia(UUID membresiaId);
}
