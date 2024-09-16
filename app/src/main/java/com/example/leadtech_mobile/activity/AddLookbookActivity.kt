package com.example.leadtech_mobile.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.Lookbook
import com.example.leadtech_mobile.viewModel.LookbookViewModel

class AddLookbookActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lookbook)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)

        val edtNome = findViewById<EditText>(R.id.editTextLookbookName)
        val btnSalvar = findViewById<Button>(R.id.buttonSaveLookbook)
        val btnCancelar = findViewById<Button>(R.id.buttonCancel)

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()

            if (nome.isNotEmpty()) {
                val lookbook = Lookbook(id = generateId(), nome = nome, pecas = emptyList())
                lookbookViewModel.adicionarLookbook(lookbook)
                Toast.makeText(this, "Lookbook adicionado com sucesso!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, insira um nome para o lookbook.", Toast.LENGTH_LONG).show()
            }
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun generateId(): String {
        return System.currentTimeMillis().toString()
    }
}