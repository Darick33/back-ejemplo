package com.appgym.domain.notasventas;

import com.appgym.domain.progresosfisicos.DatosActualizarProgresoFisico;
import com.appgym.domain.progresosfisicos.DatosRegistrarProgresoFisico;
import com.appgym.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notas_ventas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class NotaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String numero;
    private String puntoEmision;
    private String establecimiento;
    private LocalDate fecha;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "notaVenta", cascade = CascadeType.ALL)
    private List<DetalleNota> detalles = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;
    private Boolean estado;

    // Generar nota de venta dto
    public NotaVenta(DatosGuardarNotaVenta datosGuardarNotaVenta, Usuario usuario) {
        this.numero = datosGuardarNotaVenta.numero();
        this.puntoEmision = datosGuardarNotaVenta.puntoEmision();
        this.establecimiento = datosGuardarNotaVenta.establecimiento();
        this.fecha = datosGuardarNotaVenta.fecha();
        this.tipoPago = datosGuardarNotaVenta.tipoPago();
        this.usuario = usuario;
        this.total = datosGuardarNotaVenta.total();
        this.estado = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Iniciar un nueva Nota de Venta
//    public NotaVenta(String numero, Usuario usuario) {
//        this.numero = numero;
//        this.puntoEmision = "001";
//        this.establecimiento = "001";
//        this.fecha = LocalDate.now();
//        this.tipoPago = TipoPago.Efectivo;
//        this.usuario = usuario;
//    }

    public void agregarDetalle(DetalleNota detalleNota) {
        detalleNota.setNotaVenta(this); // Agrega la referencia notaVenta al detalle
        this.detalles.add(detalleNota);
//        this.total = this.total.add(detalleNota.getTotal()); // Sumando del total
    }

    public void actualizarValores() {
        this.total = BigDecimal.ZERO;

        detalles.forEach(detalleCompra -> {
            this.total = this.total.add(detalleCompra.getTotal()); // Sumando del total
        });
    }

    public void anular() {
        this.estado = false;
    }


}
