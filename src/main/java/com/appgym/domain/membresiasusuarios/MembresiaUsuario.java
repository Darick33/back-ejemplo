package com.appgym.domain.membresiasusuarios;

import com.appgym.domain.email.Email;
import com.appgym.domain.membresias.HistorialMembresia;
import com.appgym.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "membresias_usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class MembresiaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime fechaCreacion;
    private String observaciones;
    @Enumerated(EnumType.STRING)
    private Fase fase;
    @OneToOne
    @JoinColumn(name = "historial_membresia_id")
    private HistorialMembresia historialMembresia;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Boolean estado;

    // Generar membresia usuario dto
    public MembresiaUsuario(DatosGuardarMembresiaUsuario datosGuardarMembresiaUsuario, HistorialMembresia historialMembresia, Usuario usuario) {
        this.fechaInicio = datosGuardarMembresiaUsuario.fechaInicio();
        this.fechaCreacion = LocalDateTime.now();
        this.observaciones = datosGuardarMembresiaUsuario.observaciones();
        this.fase = datosGuardarMembresiaUsuario.fase();
        this.historialMembresia = historialMembresia;
        this.usuario = usuario;
        this.estado = true;
        this.calcularFechaFin(); // Calcular fecha fin
    }

    protected void calcularFechaFin() {
        LocalDateTime fechaFinal = null;

        double duracion = Double.parseDouble(this.historialMembresia.getDuracion().toString());

        switch (this.historialMembresia.getTipoDuracion()) {
            case HOUR:
                fechaFinal = this.fechaInicio.plusDays((long) duracion);
                break;
            case DAY:
                fechaFinal = this.fechaInicio.plusDays((long) duracion);
                break;
            case MONTH:
                fechaFinal = this.fechaInicio.plusMonths((long) duracion);
                break;
            case YEAR:
                fechaFinal = this.fechaInicio.plusYears((long) duracion);
                break;
        }

        this.fechaFin = fechaFinal;
    }

    // Si la fecha actual es igual o posterior a la fecha fin actualiza la fase a caducada
    // Envia un correo al usuario de que su membresia a caducado.
    public void validarCaducidad() {
        LocalDateTime fechaActual = LocalDateTime.now();

        int comparacion = fechaActual.compareTo(this.fechaFin);

        if(comparacion >= 0 && this.fase == Fase.Activa) {
            this.fase = Fase.Caducada;
            Thread notificarThread = new Thread(() -> {
                // Notificamos al usuario por correo una sola ves en vase a su atributo caducando notificar
                Email email = new Email(this.usuario.getEmail(), "ByteBurst",
                        " <h1>Información</h1>\r\n"
                                + "    <p>Hola " + this.usuario.getNombre() + ", tu membresía en MAU'S GYM ha caducado. Renueva ahora para seguir disfrutando de todos nuestros servicios. Te puedes acercar a nuestras instalaciones para renovarla. </p>\r\n"
                                + "    <h3>¡Esperamos verte pronto!</h3>\r\n"
                                + "    <h3>Atentamente,</h3>\r\n"
                                + "    <h3>El Equipo de MAU'S GYM</h3>");
                if(!email.sendEmail()) {
                    System.out.println("Ocurrio un error");
                }
            });

            notificarThread.start();
        }
    }

    public void anular() {
        this.fase = Fase.Anulada;
    }
}
