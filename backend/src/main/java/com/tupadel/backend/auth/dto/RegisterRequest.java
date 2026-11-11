package com.tupadel.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(max = 20) String nombre,
        @NotBlank @Size(max = 20) String apellido1,
        @Size(max = 20) String apellido2,
        @NotBlank @Email @Size(max = 254) String correo,
        @NotBlank @Size(max = 20) String dni,
        @NotBlank @Size(min = 8, max = 72) String password
) {
}