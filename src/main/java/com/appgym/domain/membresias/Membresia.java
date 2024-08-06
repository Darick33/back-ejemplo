package com.appgym.domain.membresias;

import com.appgym.domain.ivas.Iva;
import jakarta.persistence.*;
import lombok.*;
import org.apache.el.lang.ELArithmetic;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "membresias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

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
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    private String observaciones;

    public Membresia(DatosGuardarMembresia datosGuardarMembresia) {
        this.nombre = datosGuardarMembresia.nombre();
        this.descripcion = datosGuardarMembresia.descripcion();
        this.precio = new BigDecimal("0.0");
        this.total = datosGuardarMembresia.total();
//        calcularPrecio(); // precio sin iva
        this.duracion = datosGuardarMembresia.duracion();
        this.tipoDuracion = datosGuardarMembresia.tipoDuracion();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.observaciones = datosGuardarMembresia.observaciones();

    }
    public void calcularPrecio(){
        this.precio = new BigDecimal("0.0");
        BigDecimal uno = BigDecimal.ONE;
        this.precio = this.total.divide(this.iva.getIva().add(uno), 2, BigDecimal.ROUND_HALF_UP);
    }

    public void actualizarDatos(DatosActualizarMembresia datosActualizarMembresia) {
        if(datosActualizarMembresia.nombre() != null) {
            this.nombre = datosActualizarMembresia.nombre();
        }
        if(datosActualizarMembresia.descripcion() != null) {
            this.descripcion= datosActualizarMembresia.descripcion();
        }

        if(datosActualizarMembresia.precio() != null) {
            this.precio = datosActualizarMembresia.precio();
        }

        if(datosActualizarMembresia.total() != null) {
            this.total = datosActualizarMembresia.total();
        }
        if(datosActualizarMembresia.duracion() != null) {
            this.duracion = datosActualizarMembresia.duracion();
        }
        if(datosActualizarMembresia.tipoDuracion() != null) {
            this.tipoDuracion =datosActualizarMembresia.tipoDuracion();
        }
        if(datosActualizarMembresia.estado() != null) {
            this.estado = datosActualizarMembresia.estado();
        }
        if(datosActualizarMembresia.observaciones() != null) {
            this.observaciones = datosActualizarMembresia.observaciones();
        }


    }

    public void cambiarEstado() {

        this.estado = !this.estado;
    }
}
