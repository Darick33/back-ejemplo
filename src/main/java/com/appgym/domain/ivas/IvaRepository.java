package com.appgym.domain.ivas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.UUID;

public interface IvaRepository extends JpaRepository<Iva, UUID> {
    @Query("""
        select i from Iva i where i.fechaRegistro = (select max(i2.fechaRegistro) from Iva i2)   
    """)
    Iva findLastId();
}
