package com.example.leadtech_mobile.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.adapter.LookbookAdapter
import com.example.leadtech_mobile.viewModel.LookbookViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var lookbookAdapter: LookbookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        // Inicializa ViewModel
        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)

        // Configura RecyclerView
        recyclerView = findViewById(R.id.recyclerViewLookbooks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        lookbookAdapter = LookbookAdapter { lookbook ->
            // Ao clicar no lookbook, vai para LookbookDetailsActivity
            val intent = Intent(this, LookbookDetailsActivity::class.java)
            intent.putExtra("LOOKBOOK_ID", lookbook.id)
            startActivity(intent)
        }
        recyclerView.adapter = lookbookAdapter

        // Observa a lista de lookbooks do ViewModel
        lookbookViewModel.lookbooks.observe(this, { lookbooks ->
            lookbookAdapter.submitList(lookbooks)
        })

        // Botão para criar um novo lookbook
        val btnAddLookbook = findViewById<FloatingActionButton>(R.id.btnAddLookbook)
        btnAddLookbook.setOnClickListener {
            // Vai para a tela de criação de novo lookbook
            val intent = Intent(this, AddLookbookActivity::class.java)
            startActivity(intent)
        }

        // Botão para sugestões automáticas de looks
        val btnSuggestions = findViewById<FloatingActionButton>(R.id.btnSuggestions)
        btnSuggestions.setOnClickListener {
            val intent = Intent(this, SuggestionsActivity::class.java)
            startActivity(intent)
        }
    }
}
