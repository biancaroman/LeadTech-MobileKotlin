package com.example.leadtech_mobile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leadtech_mobile.model.Lookbook
import com.example.leadtech_mobile.repository.LookbookRepository

class LookbookViewModel(application: Application) : AndroidViewModel(application) {

    private val lookbookRepository: LookbookRepository = LookbookRepository()

    private val _lookbooks = MutableLiveData<List<Lookbook>>()
    val lookbooks: LiveData<List<Lookbook>>
        get() = _lookbooks

    private val _lookbookDetails = MutableLiveData<Lookbook>()
    val lookbookDetails: LiveData<Lookbook>
        get() = _lookbookDetails

    init {
        // Adiciona dados de exemplo
        val exampleLookbook1 = Lookbook(id = "1", nome = "Lookbook Primavera", pecas = emptyList())
        val exampleLookbook2 = Lookbook(id = "2", nome = "Lookbook Verão", pecas = emptyList())
        val exampleLookbook3 = Lookbook(id = "3", nome = "Lookbook Outono", pecas = emptyList())

        lookbookRepository.addLookbook(exampleLookbook1)
        lookbookRepository.addLookbook(exampleLookbook2)
        lookbookRepository.addLookbook(exampleLookbook3)

        carregarLookbooks()
    }

    fun carregarLookbooks() {
        _lookbooks.value = lookbookRepository.getAllLookbooks()
    }

    fun getLookbookById(lookbookId: String): LiveData<Lookbook> {
        _lookbookDetails.value = lookbookRepository.getLookbookById(lookbookId)
        return lookbookDetails
    }

    fun adicionarLookbook(lookbook: Lookbook) {
        lookbookRepository.addLookbook(lookbook)
    }

    fun updateLookbook(lookbook: Lookbook) {
        lookbookRepository.updateLookbook(lookbook)
    }

    fun excluirLookbook(lookbookId: String) {
        lookbookRepository.deleteLookbook(lookbookId)
    }

    fun exportarLookbookParaPdf(lookbookId: String) {
        lookbookRepository.exportLookbookToPdf(lookbookId)
    }
}
