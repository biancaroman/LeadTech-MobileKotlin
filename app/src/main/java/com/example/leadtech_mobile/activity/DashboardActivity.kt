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
import com.example.leadtech_mobile.viewModel.UsuarioViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {

    private lateinit var lookbookViewModel: LookbookViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var lookbookAdapter: LookbookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        lookbookViewModel = ViewModelProvider(this).get(LookbookViewModel::class.java)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

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

        // Realizar o logout ao clicar no perfil
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_favorites -> {
                    usuarioViewModel.logout(this) // Realiza o logout
                    startActivity(Intent(this, HomeActivity::class.java)) // Redireciona para a HomeActivity
                    finish() // Finaliza a atividade atual
                    true
                }
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java)) // Redireciona para a HomeActivity
                    finish() // Finaliza a atividade atual
                    true
                }
                else -> false // Para outros itens, não faz nada
            }
        }

        verificarSessao() // Chama o método para verificar a sessão
    }

    private fun verificarSessao() {
        if (!usuarioViewModel.verificarSessaoUsuario(this)) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
