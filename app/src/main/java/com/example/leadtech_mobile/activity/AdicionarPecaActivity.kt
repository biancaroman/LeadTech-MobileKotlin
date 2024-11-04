package com.example.leadtech_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.Categoria
import com.example.leadtech_mobile.model.Tamanho
import com.example.leadtech_mobile.model.PecaRoupa
import com.example.leadtech_mobile.repository.PecaRoupaRepository
import java.util.UUID

class AdicionarPecaActivity : AppCompatActivity() {

    private val pecaRoupaRepository = PecaRoupaRepository()

    private lateinit var edtNome: EditText
    private lateinit var edtCor: EditText
    private lateinit var edtUrlImagem: EditText
    private lateinit var edtBuscarNome: EditText
    private lateinit var spinnerCategoria: Spinner
    private lateinit var spinnerTamanho: Spinner
    private lateinit var btnSalvarPeca: Button
    private lateinit var btnBuscarPeca: Button
    private lateinit var btnCancelar: Button
    private lateinit var txtResultadoBusca: TextView
    private lateinit var iconEditar: ImageView
    private lateinit var iconDeletar: ImageView

    private var lookbookId: String? = null
    private var currentPeca: PecaRoupa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_peca)

        // Recebe o lookbookId passado por LookbookDetailsActivity
        lookbookId = intent.getStringExtra("LOOKBOOK_ID")

        // Inicialize os componentes da interface
        edtNome = findViewById(R.id.edtNomePeca)
        edtCor = findViewById(R.id.edtCorPeca)
        edtUrlImagem = findViewById(R.id.edtUrlImagem)
        edtBuscarNome = findViewById(R.id.edtBuscarNome)
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        spinnerTamanho = findViewById(R.id.spinnerTamanho)
        btnSalvarPeca = findViewById(R.id.btnSalvarPeca)
        btnBuscarPeca = findViewById(R.id.btnBuscarPeca)
        btnCancelar = findViewById(R.id.btnCancelar) // Botão para cancelar a adição
        txtResultadoBusca = findViewById(R.id.txtResultadoBusca)
        iconEditar = findViewById(R.id.iconEditar)
        iconDeletar = findViewById(R.id.iconDeletar)

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

        btnSalvarPeca.setOnClickListener {
            adicionarNovaPeca()
        }

        btnBuscarPeca.setOnClickListener {
            buscarPecaRoupa()
        }

        btnCancelar.setOnClickListener {
            voltarParaLookbookDetails()
        }

        iconEditar.setOnClickListener {
            currentPeca?.let { peca ->
                // Inicia a EditarPecaActivity com o ID da peça
                val intent = Intent(this, EditarPecaActivity::class.java)
                intent.putExtra("PECA_ID", peca.id)
                startActivity(intent)
            }
        }

        iconDeletar.setOnClickListener {
            currentPeca?.let { deletarPecaRoupa(it.id) }
        }
    }

    private fun adicionarNovaPeca() {
        val nome = edtNome.text.toString()
        val cor = edtCor.text.toString()
        val urlImagem = edtUrlImagem.text.toString()
        val categoria = spinnerCategoria.selectedItem as Categoria
        val tamanho = spinnerTamanho.selectedItem as Tamanho

        val novaPeca = PecaRoupa(
            id = UUID.randomUUID().toString(),
            nome = nome,
            categoria = categoria,
            cor = cor,
            tamanho = tamanho,
            urlImagem = urlImagem
        )

        pecaRoupaRepository.addPecaRoupa(novaPeca) { sucesso ->
            if (sucesso) {
                lookbookId?.let { id ->
                    pecaRoupaRepository.adicionarPecaAoLookbook(id, novaPeca) { sucessoLookbook ->
                        runOnUiThread {
                            if (sucessoLookbook) {
                                Toast.makeText(this, "Peça adicionada ao Lookbook!", Toast.LENGTH_SHORT).show()
                                voltarParaLookbookDetails() // Redireciona para LookbookDetailsActivity
                            } else {
                                Toast.makeText(this, "Erro ao adicionar a peça ao Lookbook.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this, "Erro ao adicionar a peça.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun buscarPecaRoupa() {
        val nomeBusca = edtBuscarNome.text.toString()
        pecaRoupaRepository.getPecaRoupaByNome(nomeBusca) { peca ->
            runOnUiThread {
                if (peca != null) {
                    currentPeca = peca
                    txtResultadoBusca.text = peca.nome
                    txtResultadoBusca.visibility = View.VISIBLE
                    iconEditar.visibility = View.VISIBLE
                    iconDeletar.visibility = View.VISIBLE
                } else {
                    txtResultadoBusca.text = "Peça não encontrada"
                    txtResultadoBusca.visibility = View.VISIBLE
                    iconEditar.visibility = View.GONE
                    iconDeletar.visibility = View.GONE
                }
            }
        }
    }

    private fun deletarPecaRoupa(pecaId: String) {
        pecaRoupaRepository.deletePecaRoupa(pecaId) { sucesso ->
            runOnUiThread {
                if (sucesso) {
                    Toast.makeText(this, "Peça deletada com sucesso!", Toast.LENGTH_SHORT).show()
                    limparCampos()
                } else {
                    Toast.makeText(this, "Erro ao deletar a peça.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun limparCampos() {
        edtNome.text.clear()
        edtCor.text.clear()
        edtUrlImagem.text.clear()
        edtBuscarNome.text.clear()
        spinnerCategoria.setSelection(0)
        spinnerTamanho.setSelection(0)
        txtResultadoBusca.visibility = View.GONE
        iconEditar.visibility = View.GONE
        iconDeletar.visibility = View.GONE
        currentPeca = null
    }

    private fun voltarParaLookbookDetails() {
        val intent = Intent(this, LookbookDetailsActivity::class.java)
        intent.putExtra("LOOKBOOK_ID", lookbookId) // Passa o ID do lookbook de volta
        startActivity(intent)
        finish() // Fecha a Activity atual
    }
}
