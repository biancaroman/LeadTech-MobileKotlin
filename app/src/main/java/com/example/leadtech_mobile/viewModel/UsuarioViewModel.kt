package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {

    fun login(email: String, password: String, callback: LoginCallback) {
        // Simulação de autenticação - substitua pela sua lógica real
        if (email == "user@example.com" && password == "password123") {
            // Login bem-sucedido
            callback.onSuccess()
        } else {
            // Falha no login
            callback.onFailure()
        }
    }
}

// Interface para o callback de login
interface LoginCallback {
    fun onSuccess()
    fun onFailure()
}
