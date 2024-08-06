package com.appgym.domain.formapago;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormaPagoRepository extends JpaRepository<FormaPago, UUID> {
}
