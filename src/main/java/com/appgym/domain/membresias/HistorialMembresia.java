package com.appgym.domain.membresias;

import com.appgym.domain.ivas.Iva;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historiales_membresias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class HistorialMembresia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private LocalDateTime fechaRegistro ;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @ManyToOne
    @JoinColumn(name = "iva_id")
    private Iva iva;
    private BigDecimal total;
    private BigDecimal duracion;
    @Enumerated(EnumType.STRING)
    private TipoDuracion tipoDuracion;
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "membresia_id")
    private Membresia membresia;


    public HistorialMembresia (Membresia membresia){
        this.fechaRegistro = LocalDateTime.now();
        this.nombre = membresia.getNombre();
        this.descripcion = membresia.getDescripcion();
        this.precio = membresia.getPrecio();
        this.total = membresia.getTotal();
        this.duracion = membresia.getDuracion();
        this.tipoDuracion = membresia.getTipoDuracion();
        this.observaciones = membresia.getObservaciones();
        this.membresia = membresia;
    }
}


