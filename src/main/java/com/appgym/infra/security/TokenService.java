package com.appgym.infra.security;

import com.appgym.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.create()
                    .withIssuer("appgym")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId().toString())
                    .withClaim("rol", usuario.getRol().name())
                    .withExpiresAt(generarTiempoExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        DecodedJWT verifier = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            verifier = JWT.require(algorithm)
                    .withIssuer("appgym")
                    .build()
                    .verify(token);

            verifier.getSubject();

        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        if (verifier != null) {
            String subject = verifier.getSubject();
            if (subject != null) {
                return subject;
            }
        }

        throw new ValidationException("Verifier inválido");

    }

    private Instant generarTiempoExpiracion() {
        return LocalDateTime.now().plusHours(240).toInstant(ZoneOffset.of("-05:00"));
    }
}