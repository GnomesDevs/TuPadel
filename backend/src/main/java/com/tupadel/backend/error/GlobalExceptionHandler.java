package com.tupadel.backend.error;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * Traduce las excepciones de la API al esquema ErrorDetalle del OpenAPI ({codigo, mensaje}).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MENSAJE_CREDENCIALES = "Credenciales inválidas";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetalle> validacion(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining("; "));
        if (mensaje.isBlank()) {
            mensaje = "Datos no válidos";
        }
        return respuesta(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetalle> cuerpoIlegible(HttpMessageNotReadableException e) {
        return respuesta(HttpStatus.BAD_REQUEST, "El cuerpo de la petición falta o no es un JSON válido");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetalle> estado(ResponseStatusException e) {
        HttpStatus estado = HttpStatus.valueOf(e.getStatusCode().value());
        String mensaje = e.getReason();
        if (mensaje == null || mensaje.isBlank()) {
            mensaje = estado == HttpStatus.UNAUTHORIZED ? MENSAJE_CREDENCIALES : estado.getReasonPhrase();
        }
        return respuesta(estado, mensaje);
    }

    private ResponseEntity<ErrorDetalle> respuesta(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(new ErrorDetalle(estado.value(), mensaje));
    }
}