package com.appgym.domain.progresosfisicos;

import com.appgym.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "progresos_fisicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class ProgresoFisico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private LocalDateTime fechaRegistro;
    private BigDecimal peso = BigDecimal.ZERO;
    private BigDecimal altura = BigDecimal.ZERO;
    private BigDecimal espalda = BigDecimal.ZERO;
    private BigDecimal pecho = BigDecimal.ZERO;
    private BigDecimal cintura = BigDecimal.ZERO;
    private BigDecimal cadera = BigDecimal.ZERO;
    private BigDecimal biceps = BigDecimal.ZERO;
    private BigDecimal muslos = BigDecimal.ZERO;
    private BigDecimal antebrazos = BigDecimal.ZERO;
    private BigDecimal pantorillas = BigDecimal.ZERO;
    private String observaciones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ProgresoFisico(DatosRegistrarProgresoFisico datosRegistrarProgresoFisico, Usuario usuario) {
        this.fechaRegistro = LocalDateTime.now(); // Fecha actual
        this.peso = datosRegistrarProgresoFisico.peso();
        this.altura = datosRegistrarProgresoFisico.altura();
        this.espalda = datosRegistrarProgresoFisico.espalda();
        this.pecho = datosRegistrarProgresoFisico.pecho();
        this.cintura = datosRegistrarProgresoFisico.cintura();
        this.cadera = datosRegistrarProgresoFisico.cadera();
        this.biceps = datosRegistrarProgresoFisico.biceps();
        this.muslos = datosRegistrarProgresoFisico.muslos();
        this.antebrazos = datosRegistrarProgresoFisico.antebrazos();
        this.pantorillas = datosRegistrarProgresoFisico.pantorillas();
        this.observaciones = datosRegistrarProgresoFisico.observaciones();
        this.usuario = usuario;
    }

    public void actualizarDatos(DatosActualizarProgresoFisico datosActualizarProgresoFisico) {
        if (datosActualizarProgresoFisico.fechaRegistro() != null) {
            this.fechaRegistro = datosActualizarProgresoFisico.fechaRegistro();
        }
        if (datosActualizarProgresoFisico.peso() != null) {
            this.peso = datosActualizarProgresoFisico.peso();
        }
        if (datosActualizarProgresoFisico.altura() != null) {
            this.altura = datosActualizarProgresoFisico.altura();
        }
        if (datosActualizarProgresoFisico.espalda() != null) {
            this.espalda = datosActualizarProgresoFisico.espalda();
        }
        if (datosActualizarProgresoFisico.pecho() != null) {
            this.pecho = datosActualizarProgresoFisico.pecho();
        }
        if (datosActualizarProgresoFisico.cintura() != null) {
            this.cintura = datosActualizarProgresoFisico.cintura();
        }
        if (datosActualizarProgresoFisico.cadera() != null) {
            this.cadera = datosActualizarProgresoFisico.cadera();
        }
        if (datosActualizarProgresoFisico.biceps() != null) {
            this.biceps = datosActualizarProgresoFisico.biceps();
        }
        if (datosActualizarProgresoFisico.muslos() != null) {
            this.muslos = datosActualizarProgresoFisico.muslos();
        }
        if (datosActualizarProgresoFisico.antebrazos() != null) {
            this.antebrazos = datosActualizarProgresoFisico.antebrazos();
        }
        if (datosActualizarProgresoFisico.pantorillas() != null) {
            this.pantorillas = datosActualizarProgresoFisico.pantorillas();
        }
        if (datosActualizarProgresoFisico.observaciones() != null && !datosActualizarProgresoFisico.observaciones().isEmpty()) {
            this.observaciones = datosActualizarProgresoFisico.observaciones();
        }
    }


}
