package com.example.tupadel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var uiState by mutableStateOf<LoginUiState>(LoginUiState.Idle)
        private set

    fun onLoginClick() {
        if (email.isBlank() || password.isBlank()) {
            uiState = LoginUiState.Error("El correo y la contraseña son obligatorios")
            return
        }

        uiState = LoginUiState.Loading

        // Lanzamos una corrutina en el scope del ViewModel para no bloquear el hilo principal
        viewModelScope.launch {
            try {
                val request = LoginRequest(email, password)
                // Llamada real al backend levantado en tu Docker
                val response = RetrofitClient.apiService.login(request)

                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()?.token
                    // Aquí podrías guardar el token temporalmente si lo necesitas
                    uiState = LoginUiState.Success
                } else {
                    // Si el servidor responde con error (ej. 401 credenciales incorrectas)
                    uiState = LoginUiState.Error("Correo o contraseña incorrectos")
                }
            } catch (e: Exception) {
                // Si falla la conexión (ej. el backend de Docker está apagado)
                uiState = LoginUiState.Error("Error de conexión con el servidor")
            }
        }
    }
}