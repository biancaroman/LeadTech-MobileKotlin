package com.example.leadtech_mobile.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.Categoria
import com.example.leadtech_mobile.model.Tamanho
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.repository.PecaRoupaRepository

class EditarPecaActivity : AppCompatActivity() {

    private val pecaRoupaRepository = PecaRoupaRepository()
    private lateinit var edtNome: EditText
    private lateinit var edtCor: EditText
    private lateinit var edtUrlImagem: EditText
    private lateinit var spinnerCategoria: Spinner
    private lateinit var spinnerTamanho: Spinner
    private lateinit var btnAtualizarPeca: Button
    private var pecaId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_peca)

        // Recebe o ID da peça a ser editada
        pecaId = intent.getStringExtra("PECA_ID")

        edtNome = findViewById(R.id.edtNomePeca)
        edtCor = findViewById(R.id.edtCorPeca)
        edtUrlImagem = findViewById(R.id.edtUrlImagem)
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        spinnerTamanho = findViewById(R.id.spinnerTamanho)
        btnAtualizarPeca = findViewById(R.id.btnAtualizarPeca)

        // Configurar os Spinners
        spinnerCategoria.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Categoria.values()
        )
        spinnerTamanho.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Tamanho.values()
        )

        // Carregar dados da peça para edição
        carregarDadosPeca()

        btnAtualizarPeca.setOnClickListener {
            atualizarPeca()
        }
    }

    private fun carregarDadosPeca() {
        pecaId?.let { id ->
            pecaRoupaRepository.getPecaRoupaByNome(id) { peca ->
                if (peca != null) {
                    edtNome.setText(peca.nome)
                    edtCor.setText(peca.cor)
                    edtUrlImagem.setText(peca.urlImagem)
                    spinnerCategoria.setSelection(Categoria.values().indexOf(peca.categoria))
                    spinnerTamanho.setSelection(Tamanho.values().indexOf(peca.tamanho))
                } else {
                    Toast.makeText(this, "Peça não encontrada.", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a activity se não encontrar a peça
                }
            }
        }
    }

    private fun atualizarPeca() {
        val nome = edtNome.text.toString()
        val cor = edtCor.text.toString()
        val urlImagem = edtUrlImagem.text.toString()
        val categoria = spinnerCategoria.selectedItem as Categoria
        val tamanho = spinnerTamanho.selectedItem as Tamanho

        val pecaAtualizada = PecaRoupa(
            id = pecaId ?: "",
            nome = nome,
            categoria = categoria,
            cor = cor,
            tamanho = tamanho,
            urlImagem = urlImagem
        )

        pecaRoupaRepository.updatePecaRoupa(pecaAtualizada) { sucesso ->
            if (sucesso) {
                Toast.makeText(this, "Peça atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao atualizar a peça.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
