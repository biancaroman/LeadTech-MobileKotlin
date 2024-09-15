package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.model.Categoria
import com.example.leadtech_mobile.model.Tamanho
import com.example.leadtech_mobile.repository.PecaRoupaRepository

class PecaRoupaViewModel : ViewModel() {

    private val pecaRoupaRepository = PecaRoupaRepository()
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun adicionarPecaAoLookbook(lookbookId: String) {
        // Cria uma peça de roupa de exemplo
        val novaPeca = PecaRoupa(
            id = "newId",
            nome = "Nova Peça",
            categoria = Categoria.CAMISA,
            cor = "Azul",
            tamanho = Tamanho.M,
            urlImagem = "https://example.com/image.jpg"
        )

        // Adiciona a peça ao lookbook
        viewModelScope.launch {
            pecaRoupaRepository.adicionarPecaAoLookbook(lookbookId, novaPeca)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel() // Cancela o escopo quando o ViewModel é destruído
    }
}
