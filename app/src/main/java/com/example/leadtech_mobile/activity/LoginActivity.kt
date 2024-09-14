package com.example.leadtech_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.viewModel.LoginCallback
import com.example.leadtech_mobile.viewModel.UsuarioViewModel
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        val edtEmail = findViewById<EditText>(R.id.editTextEmail)
        val edtPassword = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                usuarioViewModel.login(email, password, object : LoginCallback {
                    override fun onSuccess() {
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure() {
                        Toast.makeText(this@LoginActivity, "Falha no login. Verifique suas credenciais.", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
