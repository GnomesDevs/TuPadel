package com.example.tupadel

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correo")
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val usuario: UsuarioResumen?
)

data class UsuarioResumen(
    val id: Long?,
    val nombre: String?,
    val email: String?
)