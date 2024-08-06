package com.appgym.domain.ivas;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ivas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private BigDecimal iva;
    private LocalDateTime fechaRegistro;

    public Iva(DatosRegistrarIva datosRegistrarIva) {
        this.iva = datosRegistrarIva.iva();
        this.fechaRegistro = LocalDateTime.now();
    }

}
