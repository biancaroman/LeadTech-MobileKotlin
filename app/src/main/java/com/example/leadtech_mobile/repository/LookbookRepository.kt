package com.example.leadtech_mobile.repository

import com.example.leadtech_mobile.model.Lookbook
import okhttp3.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class LookbookRepository {
    private val BASE_URL = "https://leadtech-mobile-default-rtdb.firebaseio.com"
    private val client = OkHttpClient()
    private val gson = Gson()
    private val handler = Handler(Looper.getMainLooper())

    fun getLookbookById(id: String, callback: (Lookbook?) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks/$id.json")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LookbookRepository", "Falha ao obter lookbook: ${e.message}")
                handler.post { callback(null) }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val lookbook = gson.fromJson(body, Lookbook::class.java)
                handler.post { callback(lookbook) }
            }
        })
    }

    fun getAllLookbooks(callback: (List<Lookbook>) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks.json")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LookbookRepository", "Falha ao obter lookbooks: ${e.message}")
                handler.post { callback(emptyList()) }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val typeToken = object : TypeToken<Map<String, Lookbook>>() {}.type
                val lookbooksMap: Map<String, Lookbook> = gson.fromJson(body, typeToken)
                val lookbooksList = lookbooksMap.values.toList()
                handler.post { callback(lookbooksList) }
            }
        })
    }

    fun addLookbook(lookbook: Lookbook, callback: (Boolean) -> Unit) {
        val lookbookJson = gson.toJson(lookbook)
        val requestBody = lookbookJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks/${lookbook.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LookbookRepository", "Falha ao adicionar lookbook: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }

    fun deleteLookbook(id: String, callback: (Boolean) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks/$id.json")
            .delete()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LookbookRepository", "Falha ao excluir lookbook: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }

    fun updateLookbook(lookbook: Lookbook, callback: (Boolean) -> Unit) {
        val lookbookJson = gson.toJson(lookbook)
        val requestBody = lookbookJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/lookbooks/${lookbook.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LookbookRepository", "Falha ao atualizar lookbook: ${e.message}")
                handler.post { callback(false) }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post { callback(response.isSuccessful) }
            }
        })
    }
}
