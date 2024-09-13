package com.example.leadtech_mobile.model

data class PecaRoupa(
    val id: String,
    val nome: String,
    val categoria: Categoria,
    val cor: String,
    val tamanho: Tamanho,
    val urlImagem: String
)

enum class Categoria {
    CAMISA, CALCA, VESTIDO, SAPATO, ACESSORIO
}

enum class Tamanho {
    PP, P, M, G, GG, XG
}
