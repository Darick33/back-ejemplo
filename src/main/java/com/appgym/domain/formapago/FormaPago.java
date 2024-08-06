package com.appgym.domain.formapago;

import com.appgym.domain.notasventas.NotaVenta;
import com.appgym.domain.notasventas.TipoPago;
import com.appgym.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "formas_pagos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private LocalDateTime fecha;
    private BigDecimal monto;
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;
    @ManyToOne
    @JoinColumn(name = "nota_venta_id")
    private NotaVenta notaVenta;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
