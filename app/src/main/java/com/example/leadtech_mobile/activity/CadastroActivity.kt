package com.example.leadtech_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.Usuario
import com.example.leadtech_mobile.viewModel.CadastroCallback
import com.example.leadtech_mobile.viewModel.UsuarioViewModel

class CadastroActivity : AppCompatActivity() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var nomeEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializando as Views
        nomeEditText = findViewById(R.id.editTextNome)
        emailEditText = findViewById(R.id.editTextEmail)
        senhaEditText = findViewById(R.id.editTextPassword)
        btnCadastrar = findViewById(R.id.buttonCadastrar)

        // Inicializando a ViewModel
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        // Configurando ação do botão de cadastro
        btnCadastrar.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {
        val nome = nomeEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val senha = senhaEditText.text.toString().trim()

        // Validações básicas de campos vazios
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Criando o objeto Usuario (o id será gerado no ViewModel, se necessário)
        val usuario = Usuario(id = "", nome = nome, email = email, senha = senha)

        // Chama a função de cadastro na ViewModel
        usuarioViewModel.cadastrarUsuario(usuario, object : CadastroCallback {
            override fun onSuccess() {
                runOnUiThread {
                    // Exibe uma mensagem de sucesso e redireciona para a tela de login
                    Toast.makeText(this@CadastroActivity, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CadastroActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Fecha a tela de cadastro
                }
            }

            override fun onFailure() {
                runOnUiThread {
                    // Exibe uma mensagem de erro ao usuário
                    Toast.makeText(this@CadastroActivity, "Falha ao cadastrar o usuário. Tente novamente.", Toast.LENGTH_SHORT).show()
                    Log.e("CadastroActivity", "Erro ao cadastrar usuário no Firebase.")
                }
            }
        })
    }
}
