package com.example.leadtech_mobile.repository

import com.example.leadtech_mobile.model.Lookbook

class LookbookRepository {

    private val lookbooks = mutableListOf<Lookbook>()

    fun getLookbookById(id: String): Lookbook? {
        return lookbooks.find { it.id == id }
    }

    fun getAllLookbooks(): List<Lookbook> {
        return lookbooks
    }

    fun addLookbook(lookbook: Lookbook) {
        lookbooks.add(lookbook)
    }

    fun updateLookbook(updatedLookbook: Lookbook) {
        val index = lookbooks.indexOfFirst { it.id == updatedLookbook.id }
        if (index != -1) {
            lookbooks[index] = updatedLookbook
        }
    }

    fun deleteLookbook(id: String) {
        lookbooks.removeAll { it.id == id }
    }

    fun exportLookbookToPdf(id: String) {
        // Implementar exportação para PDF
    }
}
