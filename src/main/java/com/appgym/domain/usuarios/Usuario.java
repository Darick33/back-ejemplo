package com.appgym.domain.usuarios;

import com.appgym.domain.socios.DatosActualizarSocio;
import com.appgym.domain.socios.DatosGuardarSocio;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String email;
    private String clave;
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private Boolean sexo;
    private String direccion;
    private String celular;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    private Boolean superadmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol));
    }
    public  Usuario(DatosGuardarSocio datosGuardarSocio){
        this.nombre = datosGuardarSocio.nombre();
        this.apellido = datosGuardarSocio.apellido();
        this.email = datosGuardarSocio.email();
        this.clave = datosGuardarSocio.clave();
        this.identificacion = datosGuardarSocio.identificacion();
        this.fechaNacimiento = datosGuardarSocio.fechaNacimiento();
        this.sexo = datosGuardarSocio.sexo();
        this.direccion = datosGuardarSocio.direccion();
        this.celular = datosGuardarSocio.celular();
        this.observaciones = datosGuardarSocio.observaciones();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.superadmin = false;
        this.rol = Rol.SOCIO;

    }
    public  Usuario(DatosGuardarUsuario datosGuardarUsuario, String clave){
        this.nombre = datosGuardarUsuario.nombre();
        this.apellido = datosGuardarUsuario.apellido();
        this.email = datosGuardarUsuario.email();
        this.clave = clave;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.rol = datosGuardarUsuario.rol();
        this.superadmin = false;
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {
        if(datosActualizarUsuario.nombre() != null) {
            this.nombre = datosActualizarUsuario.nombre();
        }

        if(datosActualizarUsuario.email() != null) {
            this.email = datosActualizarUsuario.email();
        }

        if(datosActualizarUsuario.apellido() != null) {
            this.apellido = datosActualizarUsuario.apellido();
        }
        if(datosActualizarUsuario.rol() != null) {
            this.rol = datosActualizarUsuario.rol();
        }
    }
    public void actualizarDatosSocio(DatosActualizarSocio datosActualizarSocio) {
        if(datosActualizarSocio.nombre() != null) {
            this.nombre = datosActualizarSocio.nombre();
        }

        if(datosActualizarSocio.apellido() != null) {
            this.apellido = datosActualizarSocio.apellido();
        }

        if(datosActualizarSocio.identificacion() != null) {
            this.identificacion = datosActualizarSocio.identificacion();
        }

        if(datosActualizarSocio.fechaNacimiento() != null) {
            this.fechaNacimiento = datosActualizarSocio.fechaNacimiento();
        }

        if(datosActualizarSocio.direccion() != null) {
            this.direccion = datosActualizarSocio.direccion();
        }
        if(datosActualizarSocio.celular() != null) {
            this.celular = datosActualizarSocio.celular();
        }
        if(datosActualizarSocio.sexo() != null) {
            this.sexo = datosActualizarSocio.sexo();
        }
        if(datosActualizarSocio.observaciones() != null) {
            this.observaciones = datosActualizarSocio.observaciones();
        }
    }

    public void cambiarEstado() {

        this.estado = !this.estado;
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
