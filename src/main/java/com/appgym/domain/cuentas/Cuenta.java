
package com.appgym.domain.cuentas;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.web.multipart.MultipartFile;

import com.appgym.domain.membresias.DatosGuardarMembresia;
import com.appgym.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cuentas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String nombreEmpresa;
    private String nombrePropietario;
    private String email;
    private String celular_1;
    private String celular_2;
    private String direccion;
    private String logoEmpresa;
    private String logoPerfil;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Cuenta(DatosGuardarCuenta datosGuardarCuenta, Usuario usuario) {
        this.nombreEmpresa = datosGuardarCuenta.nombreEmpresa();
        this.nombrePropietario = datosGuardarCuenta.nombrePropietario();
        this.email = datosGuardarCuenta.email();
        this.celular_1 = datosGuardarCuenta.celular_1();
        this.celular_2 = datosGuardarCuenta.celular_2();
        this.direccion = datosGuardarCuenta.direccion();
        this.logoEmpresa = datosGuardarCuenta.logoEmpresa();
        this.logoPerfil = datosGuardarCuenta.logoPerfil();
        this.usuario = usuario;
    }

    public void acualizar(DatosActualizarCuenta datosActualizarCuenta) {
        if(datosActualizarCuenta.nombreEmpresa() != null) {
            this.nombreEmpresa = datosActualizarCuenta.nombreEmpresa();
        }
        if(datosActualizarCuenta.nombrePropietario() != null) {
            this.nombrePropietario = datosActualizarCuenta.nombrePropietario();
        }
        if(datosActualizarCuenta.email() != null) {
            this.email = datosActualizarCuenta.email();
        }
        if(datosActualizarCuenta.celular_1() != null) {
            this.celular_1 = datosActualizarCuenta.celular_1();
        }
        if(datosActualizarCuenta.celular_2() != null) {
            this.celular_2 = datosActualizarCuenta.celular_2();
        }
        if(datosActualizarCuenta.direccion() != null) {
            this.direccion = datosActualizarCuenta.direccion();
        }
        if(datosActualizarCuenta.logoEmpresa() != null) {
            this.logoEmpresa = datosActualizarCuenta.logoEmpresa();
        }
        if(datosActualizarCuenta.logoPerfil() != null) {
            this.logoPerfil = datosActualizarCuenta.logoPerfil();
        }
    }
}


