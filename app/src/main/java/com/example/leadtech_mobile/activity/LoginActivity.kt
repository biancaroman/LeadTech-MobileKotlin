package com.example.leadtech_mobile.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.viewModel.LoginCallback
import com.example.leadtech_mobile.viewModel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Oculta a barra de ação e ajusta a cor da barra de status
        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        // Inicializa a ViewModel
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        // Referências para os campos de texto e botões
        val edtEmail = findViewById<EditText>(R.id.editTextEmail)
        val edtPassword = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)
        val btnCadastro = findViewById<Button>(R.id.buttonCadastro)

        // Configuração do clique no botão de login
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Chama o método de login da ViewModel
                usuarioViewModel.login(email, password, object : LoginCallback {
                    override fun onSuccess() {
                        runOnUiThread {
                            Log.d("LoginActivity", "Login realizado com sucesso!")
                            Toast.makeText(this@LoginActivity, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()

                            // Navega para o DashboardActivity
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            startActivity(intent)
                            finish() // Fecha a tela de login
                        }
                    }

                    override fun onFailure() {
                        runOnUiThread {
                            Log.e("LoginActivity", "Falha no login. Credenciais inválidas.")
                            Toast.makeText(this@LoginActivity, "Falha no login. Verifique suas credenciais.", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            } else {
                runOnUiThread {
                    // Exibe um alerta se os campos estiverem vazios
                    Log.d("LoginActivity", "Campos de email ou senha não preenchidos.")
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Navegação para a tela de cadastro
        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}

