package com.example.leadtech_mobile.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.adapter.PecaRoupaAdapter
import com.example.leadtech_mobile.viewModel.LookbookViewModel
import com.example.leadtech_mobile.viewModel.PecaRoupaViewModel

class LookbookDetailsActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel
    private lateinit var pecaRoupaViewModel: PecaRoupaViewModel
    private lateinit var pecaRoupaAdapter: PecaRoupaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookbook_details)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)
        pecaRoupaViewModel = ViewModelProvider(this).get(PecaRoupaViewModel::class.java)

        val lookbookId = intent.getStringExtra("LOOKBOOK_ID") ?: return

        val edtNome = findViewById<EditText>(R.id.editTextNomeLookbook)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPecas)
        val btnAdicionarPeca = findViewById<Button>(R.id.buttonAdicionarPeca)
        val btnExcluirLookbook = findViewById<Button>(R.id.buttonExcluirLookbook)
        val btnExportarPdf = findViewById<Button>(R.id.buttonExportarPdf)

        recyclerView.layoutManager = LinearLayoutManager(this)
        pecaRoupaAdapter = PecaRoupaAdapter()
        recyclerView.adapter = pecaRoupaAdapter

        // Observe the lookbookDetails LiveData
        lookbookViewModel.lookbookDetails.observe(this) { lookbook ->
            lookbook?.let {
                edtNome.setText(it.nome)
                pecaRoupaAdapter.submitList(it.pecas)
            }
        }

        // Call the method to load the lookbook details
        lookbookViewModel.getLookbookById(lookbookId)

        btnAdicionarPeca.setOnClickListener {
            pecaRoupaViewModel.adicionarPecaAoLookbook(lookbookId)
            Toast.makeText(this, "Peça adicionada ao Lookbook", Toast.LENGTH_SHORT).show()
        }

        btnExcluirLookbook.setOnClickListener {
            lookbookViewModel.excluirLookbook(lookbookId)
            Toast.makeText(this, "Lookbook excluído", Toast.LENGTH_SHORT).show()
            finish()  // Finaliza a atividade e retorna ao Dashboard
        }

        btnExportarPdf.setOnClickListener {
            lookbookViewModel.exportarLookbookParaPdf(lookbookId)
            Toast.makeText(this, "Lookbook exportado para PDF", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        lookbookViewModel.carregarLookbooks()  // Garanta que a lista de lookbooks esteja atualizada
    }
}
