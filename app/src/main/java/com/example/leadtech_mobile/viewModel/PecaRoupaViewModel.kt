package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.repository.PecaRoupaRepository
import kotlinx.coroutines.launch

class PecaRoupaViewModel : ViewModel() {
    private val pecaRoupaRepository = PecaRoupaRepository()

    fun addPecaRoupa(pecaRoupa: PecaRoupa, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            pecaRoupaRepository.addPecaRoupa(pecaRoupa) { sucesso ->
                callback(sucesso)
            }
        }
    }
}

