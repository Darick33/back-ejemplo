package com.appgym.domain.notasventas;

import com.appgym.domain.membresiasusuarios.MembresiaUsuario;
import com.appgym.domain.productos.Producto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "detalles_notas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class DetalleNota {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private BigDecimal cantidad = BigDecimal.ZERO;
    private BigDecimal precio = BigDecimal.ZERO;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membresia_usuario_id")
    private MembresiaUsuario membresiaUsuario;
    private BigDecimal iva = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name = "nota_venta_id")
    private NotaVenta notaVenta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;



    // Generar Detalle dto membresia usuario
    public DetalleNota(DatosGuardarDetalleNota datosGuardarDetalleNota, MembresiaUsuario membresiaUsuario) {
        this.cantidad = new BigDecimal(datosGuardarDetalleNota.cantidad());
//        this.precio = new BigDecimal(datosGuardarDetalleNota.precio());
//        this.subtotal = new BigDecimal(datosGuardarDetalleNota.subtotal());
        this.total = new BigDecimal(datosGuardarDetalleNota.total());
        this.membresiaUsuario = membresiaUsuario;
//        this.iva = new BigDecimal(datosGuardarDetalleNota.iva());
        // nota venta se inserta en el metodo agregarDetalle de nota venta
    }

    // Generar detalle dto producto
    public DetalleNota(DatosGuardarDetalleNota datosGuardarDetalleNota, Producto producto) {
        this.precio = new BigDecimal(datosGuardarDetalleNota.precio());
        this.total = new BigDecimal(datosGuardarDetalleNota.total());
        this.cantidad = new BigDecimal(datosGuardarDetalleNota.cantidad());
//        this.iva = new BigDecimal(datosGuardarDetalleNota.iva());
        this.producto = producto;
        // nota venta se inserta en el metodo agregarDetalle de nota venta
    }

    public DetalleNota(MembresiaUsuario membresiaUsuario) {
        this.membresiaUsuario = membresiaUsuario;
        inicializar();
    }

    public void inicializar() {
        this.cantidad = BigDecimal.ONE;
        this.precio = this.membresiaUsuario.getHistorialMembresia().getPrecio();
        this.subtotal = this.membresiaUsuario.getHistorialMembresia().getPrecio();
        this.total = this.membresiaUsuario.getHistorialMembresia().getTotal();
        this.iva = this.total.subtract(subtotal);
    }

//    public void recalcular() {
//        this.total = this.precio.multiply(this.cantidad);
//    }
}

