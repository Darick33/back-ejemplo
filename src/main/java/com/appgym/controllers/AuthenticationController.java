package com.appgym.controllers;

import com.appgym.domain.email.Email;
import com.appgym.domain.socios.DatosRegistrarSocio;
import com.appgym.domain.socios.service.GuardarNuevoSocioService;
import com.appgym.domain.usuarios.*;
import com.appgym.domain.usuarios.autenticacion.DatosAutenticacionUsuario;
import com.appgym.domain.usuarios.autenticacion.DatosRespuestaUsuario;
import com.appgym.infra.error.ValidacionIntegridad;
import com.appgym.infra.security.DatosJWTToken;
import com.appgym.infra.security.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GuardarNuevoSocioService guardarNuevoSocioService;

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication tokenAuth = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.clave());
        try {
            var usuarioAutenticado =  authenticationManager.authenticate(tokenAuth);
            var tokenJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

            return ResponseEntity.ok(new DatosJWTToken(tokenJWT));
        } catch (Exception e) {
            throw new ValidationException("Credenciales incorrectas.");
        }
    }

    // Registrar solo socio, otros roles los creara el administrador
    @PostMapping("/register")
    public ResponseEntity<?> registerUsuario(@RequestBody @Valid DatosRegistrarSocio datosRegistrarSocio) {

//        Rol rol = Rol.valueOf(datosRegistrarUsuario.rol().toUpperCase());

        Usuario usuario = Usuario.builder()
                .email(datosRegistrarSocio.email())
                .clave(passwordEncoder.encode(datosRegistrarSocio.clave()))
                .rol(Rol.SOCIO)
                .nombre(datosRegistrarSocio.nombre())
                .apellido(datosRegistrarSocio.apellido())
                .fechaCreacion(LocalDateTime.now())
                .estado(true)
                .superadmin(false)
                .build();

        guardarNuevoSocioService.guardar(usuario);

        Thread notificarThread = new Thread(() -> {
            // Notificamos al usuario por correo una sola ves en vase a su atributo caducando notificar
            Email email = new Email(usuario.getEmail(), "ByteBurst",
                    " <h1>Bienvenido</h1>\r\n"
                            + "    <p>Hola " + usuario.getNombre() + ", nos complace que seas parte de nuestra familia de MAU'S GYM. Estamos listos para ayudarte a comenzar tu emocionante viaje hacia una vida más saludable.</p>\r\n"
                            + "    <h3>¡Bienvenido/a a la comunidad!</h3>\r\n"
                            + "    <h3>Atentamente,</h3>\r\n"
                            + "    <h3>El Equipo de MAU'S GYM</h3>");
            if(!email.sendEmail()) {
                System.out.println("Ocurrio un error");
            }
        });

        notificarThread.start();

        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody DatosJWTToken datosJWTToken) {
        String tokenJWT = datosJWTToken.tokenJWT();

        if(tokenJWT == null || tokenJWT.isEmpty()) {
            throw new ValidacionIntegridad("El token está vacio o es nulo");
        }

        var token = tokenJWT.replace("Bearer ", "");
        var subject = tokenService.getSubject(token);
        if(subject != null) {
            var usuario = usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            if(authentication.isAuthenticated()) {
                return ResponseEntity.ok("Token valido");
            }
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token es inválido o ha expirado");
    }
}
