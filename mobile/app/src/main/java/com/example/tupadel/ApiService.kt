package com.example.tupadel

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login") // La ruta exacta que definió Victor en su backend
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}