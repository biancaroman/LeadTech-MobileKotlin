package com.example.leadtech_mobile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leadtech_mobile.model.Lookbook
import com.example.leadtech_mobile.repository.LookbookRepository

class LookbookViewModel(application: Application) : AndroidViewModel(application) {

    private val lookbookRepository = LookbookRepository()

    private val _lookbooks = MutableLiveData<List<Lookbook>>()
    val lookbooks: LiveData<List<Lookbook>>
        get() = _lookbooks

    private val _lookbookDetails = MutableLiveData<Lookbook?>()
    val lookbookDetails: LiveData<Lookbook?>
        get() = _lookbookDetails

    init {
        carregarLookbooks()
    }

    fun carregarLookbooks() {
        lookbookRepository.getAllLookbooks { lookbooks ->
            _lookbooks.value = lookbooks
        }
    }

    fun getLookbookById(lookbookId: String) {
        lookbookRepository.getLookbookById(lookbookId) { lookbook ->
            _lookbookDetails.value = lookbook
        }
    }

    fun adicionarLookbook(lookbook: Lookbook) {
        lookbookRepository.addLookbook(lookbook) { sucesso ->
            if (sucesso) carregarLookbooks()  // Atualize a lista de lookbooks após adicionar
        }
    }

    fun excluirLookbook(lookbookId: String) {
        lookbookRepository.deleteLookbook(lookbookId) { sucesso ->
            if (sucesso) carregarLookbooks()  // Atualize a lista de lookbooks após exclusão
        }
    }

    fun updateLookbook(lookbook: Lookbook) {
        lookbookRepository.updateLookbook(lookbook) { sucesso ->
            if (sucesso) carregarLookbooks()  // Atualize a lista de lookbooks após atualização
        }
    }

    fun exportarLookbookParaPdf(lookbookId: String) {
        // Lógica para exportar o lookbook para PDF
    }
}

