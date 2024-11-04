package com.example.leadtech_mobile.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.leadtech_mobile.model.PecaRoupa
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class PecaRoupaRepository {
    private val BASE_URL = "https://leadtech-mobile-default-rtdb.firebaseio.com"
    private val client = OkHttpClient()
    private val gson = Gson()
    private val handler = Handler(Looper.getMainLooper())

    // Adicionar PecaRoupa
    fun addPecaRoupa(pecaRoupa: PecaRoupa, callback: (Boolean) -> Unit) {
        val pecaJson = gson.toJson(pecaRoupa)
        val requestBody = pecaJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/pecas/${pecaRoupa.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PecaRoupaRepository", "Falha ao adicionar PecaRoupa: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }

    // Obter uma PecaRoupa pelo Nome
    fun getPecaRoupaByNome(nome: String, callback: (PecaRoupa?) -> Unit) {
        // Endpoint que retorna todas as peças
        val request = Request.Builder()
            .url("$BASE_URL/pecas.json") // URL que retorna todas as peças
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PecaRoupaRepository", "Falha ao obter PecaRoupa: ${e.message}")
                handler.post { callback(null) }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                // Converte a resposta para um Map de JSON onde cada entrada é uma peça
                val pecasMap: Map<String, PecaRoupa> = gson.fromJson(body, object : TypeToken<Map<String, PecaRoupa>>() {}.type)

                // Filtra a peça pelo nome
                val pecaEncontrada = pecasMap.values.firstOrNull { it.nome.equals(nome, ignoreCase = true) }
                handler.post { callback(pecaEncontrada) }
            }
        })
    }

    // Atualizar uma PecaRoupa
    fun updatePecaRoupa(pecaRoupa: PecaRoupa, callback: (Boolean) -> Unit) {
        val pecaJson = gson.toJson(pecaRoupa)
        val requestBody = pecaJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/pecas/${pecaRoupa.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PecaRoupaRepository", "Falha ao atualizar PecaRoupa: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }

    // Deletar uma PecaRoupa
    fun deletePecaRoupa(id: String, callback: (Boolean) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/pecas/$id.json")
            .delete()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PecaRoupaRepository", "Falha ao deletar PecaRoupa: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }

    // Adicionar PecaRoupa a um Lookbook
    fun adicionarPecaAoLookbook(lookbookId: String, pecaRoupa: PecaRoupa, callback: (Boolean) -> Unit) {
        val pecaJson = gson.toJson(pecaRoupa)
        val requestBody = pecaJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks/$lookbookId/pecas/${pecaRoupa.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PecaRoupaRepository", "Erro ao adicionar peça ao lookbook: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }
}
