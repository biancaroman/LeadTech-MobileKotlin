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
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var lookbookAdapter: LookbookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerViewLookbooks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        lookbookAdapter = LookbookAdapter { lookbook ->
            val intent = Intent(this, LookbookDetailsActivity::class.java)
            intent.putExtra("LOOKBOOK_ID", lookbook.id)
            startActivity(intent)
        }
        recyclerView.adapter = lookbookAdapter

        lookbookViewModel.lookbooks.observe(this, { lookbooks ->
            lookbookAdapter.submitList(lookbooks)
        })

        val btnAddLookbook = findViewById<MaterialButton>(R.id.btnAddLookbook)
        btnAddLookbook.setOnClickListener {
            val intent = Intent(this, AddLookbookActivity::class.java)
            startActivity(intent)
        }

        val btnSuggestions = findViewById<MaterialButton>(R.id.btnSuggestions)
        btnSuggestions.setOnClickListener {
            val intent = Intent(this, SuggestionsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        lookbookViewModel.carregarLookbooks()  // Atualiza a lista de lookbooks ao retomar a atividade
    }
}
