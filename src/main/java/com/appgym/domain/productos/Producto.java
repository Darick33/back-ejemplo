package com.appgym.domain.productos;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String codigo;
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private BigDecimal precio;
    private BigDecimal stock;
    private Boolean estado;
    private LocalDateTime fechaCreacion ;


    public Producto(DatosGuardarProducto datosGuardarProducto, Categoria categoria) {
        this.codigo = datosGuardarProducto.codigo();
        this.nombre = datosGuardarProducto.nombre();
        this.descripcion = datosGuardarProducto.descripcion();
        this.categoria = categoria;
        this.precio = datosGuardarProducto.precio();
        this.stock = datosGuardarProducto.stock();
        this.estado = true;
        this.fechaCreacion = LocalDateTime.now();

    }

    public void actualizarDatosProducto(DatosActualizarProducto datosActualizarProducto ,Categoria categoria) {
        if(datosActualizarProducto.codigo() != null) {
            this.codigo = datosActualizarProducto.codigo();
        }
        if(datosActualizarProducto.nombre() != null) {
            this.nombre = datosActualizarProducto.nombre();
        }

        if(datosActualizarProducto.descripcion() != null) {
            this.descripcion = datosActualizarProducto.descripcion();
        }
        if(datosActualizarProducto.categoria_id() != null) {
            this.categoria = categoria;
        }
        if(datosActualizarProducto.precio() != null) {
            this.precio = datosActualizarProducto.precio();
        }
        if(datosActualizarProducto.stock()!= null) {
            this.stock = datosActualizarProducto.stock();
        }

    }

    public void actualizarStock(BigDecimal cantidad) { this.stock = this.stock.subtract(cantidad);}

    public void cambiarEstado() {
        this.estado = !this.estado;
    }
}
