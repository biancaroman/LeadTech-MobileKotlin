package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.model.Categoria
import com.example.leadtech_mobile.model.Tamanho

class SuggestionsViewModel : ViewModel() {

    // Gera sugestões de roupas (exemplo simplificado)
    fun generateSuggestions(): List<PecaRoupa> {
        // Lógica de sugestão (exemplo estático, substitua com sua lógica real)
        return listOf(
            PecaRoupa(
                id = "suggestion1",
                nome = "Peça Sugestão 1",
                categoria = Categoria.CAMISA,
                cor = "Vermelho",
                tamanho = Tamanho.G,
                urlImagem = "https://example.com/image1.jpg"
            ),
            PecaRoupa(
                id = "suggestion2",
                nome = "Peça Sugestão 2",
                categoria = Categoria.CALCA,
                cor = "Preto",
                tamanho = Tamanho.P,
                urlImagem = "https://example.com/image2.jpg"
            )
        )
    }
}
