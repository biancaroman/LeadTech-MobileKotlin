package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel
import com.example.leadtech_mobile.model.Usuario

class UsuarioViewModel : ViewModel() {

    // Simulação de banco de dados de usuários
    private val usuarios = listOf(
        Usuario(id = "1", nome = "Usuário Teste", email = "user@example.com", senha = "password123")
    )

    // Método de login usando o objeto Usuario
    fun login(email: String, password: String, callback: LoginCallback) {
        val usuario = usuarios.find { it.email == email && it.senha == password }
        if (usuario != null) {
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
