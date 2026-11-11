package com.tupadel.backend.auth.dto;
import com.tupadel.backend.usuario.Rol;

public record UsuarioResumen(
        Long idUsuario,
        String nombre,
        Rol rol
) {
}