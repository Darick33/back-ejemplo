package com.appgym.infra.error;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarError404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> tratarNullPointerException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidacionIntegridad.class)
    public ResponseEntity<?> errorHandlerValidacionesIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> errorHandlerValidacionesNegocio(Exception e) {
        return ResponseEntity.badRequest().body(new DatosRespuestaError(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e) {
        List<DatosErrorValidacion> datosErrorValidacion = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(datosErrorValidacion);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarRolInexistente(IllegalArgumentException e) {
        String message = e.getMessage();
        if (message != null && message.contains("No enum constant com.appgym.domain.usuarios.Rol")) {
            // La excepción se refiere a un rol inexistente
            return new ResponseEntity<>("Error: El rol especificado no es válido", HttpStatus.BAD_REQUEST);
        } else {
            // Manejar otros tipos de IllegalArgumentException aquí si es necesario
            return new ResponseEntity<>("Error: argumentos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}