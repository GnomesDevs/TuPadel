package com.tupadel.backend.auth.dto;

public record LoginResponse(
        String token,
        UsuarioResumen usuario
) {
}