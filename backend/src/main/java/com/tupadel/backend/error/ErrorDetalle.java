package com.tupadel.backend.error;

/**
 * Cuerpo de error según el esquema ErrorDetalle de docs/openapi.yaml.
 */
public record ErrorDetalle(int codigo, String mensaje) {
}