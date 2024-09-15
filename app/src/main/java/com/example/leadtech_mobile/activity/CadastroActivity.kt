package com.example.leadtech_mobile.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leadtech_mobile.R

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        // Captura os campos de cadastro
        val edtNome = findViewById<EditText>(R.id.editTextNome)
        val edtEmail = findViewById<EditText>(R.id.editTextEmail)
        val edtSenha = findViewById<EditText>(R.id.editTextPassword)
        val edtConfirmarSenha = findViewById<EditText>(R.id.editTextConfirmarSenha)
        val btnCadastrar = findViewById<Button>(R.id.buttonCadastrar)

        // Define a ação ao clicar no botão "Cadastrar"
        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString()
            val email = edtEmail.text.toString()
            val senha = edtSenha.text.toString()
            val confirmarSenha = edtConfirmarSenha.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && confirmarSenha.isNotEmpty()) {
                if (senha == confirmarSenha) {
                    // Simulação de cadastro bem-sucedido (você pode adicionar a lógica de cadastro aqui)
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

                    // Redireciona para a tela de login após o cadastro
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
        }
    }
}