package com.appgym.domain.productos;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "categorias")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;


    private String nombre;

    public Categoria(DatosguardarCategoria datosguardarCategoria) {
        this.nombre = datosguardarCategoria.nombre();
    }

    public void actualizarDatosCategoria(DatosActualizarCategoria datosActualizarCategoria) {
        if(datosActualizarCategoria.nombre() != null) {
            this.nombre = datosActualizarCategoria.nombre();
        }
    }


}
