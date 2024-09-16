package com.example.leadtech_mobile.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.model.Categoria
import com.example.leadtech_mobile.model.Tamanho
import com.example.leadtech_mobile.viewModel.LookbookViewModel
import com.example.leadtech_mobile.viewModel.SuggestionsViewModel

class SuggestionsActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel
    private lateinit var suggestionsViewModel: SuggestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestions)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)
        suggestionsViewModel = ViewModelProvider(this).get(SuggestionsViewModel::class.java)

        val textViewSuggestions = findViewById<TextView>(R.id.textViewSuggestions)
        val buttonGenerateSuggestions = findViewById<Button>(R.id.buttonGenerateSuggestions)
        val buttonAddToLookbook = findViewById<Button>(R.id.buttonAddToLookbook)
        val buttonRejectSuggestion = findViewById<Button>(R.id.buttonRejectSuggestion)

        buttonGenerateSuggestions.setOnClickListener {
            // Gera sugestões de roupas e atualiza o TextView
            val suggestions = suggestionsViewModel.generateSuggestions()
            textViewSuggestions.text = suggestions.joinToString("\n") { it.nome }
        }

        buttonAddToLookbook.setOnClickListener {
            // Adiciona a sugestão selecionada a um lookbook existente
            val selectedPeca = PecaRoupa(
                id = "newId",
                nome = "Nova peça sugerida",
                categoria = Categoria.CAMISA,
                cor = "Azul",
                tamanho = Tamanho.M,
                urlImagem = "https://example.com/image.jpg"
            )

            // Exemplo de ID do lookbook (substitua pelo real)
            val lookbookId = "lookbookId"
            lookbookViewModel.getLookbookById(lookbookId).observe(this) { lookbook ->
                if (lookbook != null) {
                    val updatedPecas = lookbook.pecas + selectedPeca
                    lookbookViewModel.updateLookbook(lookbook.copy(pecas = updatedPecas))
                }
            }
        }

        buttonRejectSuggestion.setOnClickListener {
            // Lógica para rejeitar a sugestão (exemplo simples)
            textViewSuggestions.text = "Sugestão rejeitada."
        }
    }
}

