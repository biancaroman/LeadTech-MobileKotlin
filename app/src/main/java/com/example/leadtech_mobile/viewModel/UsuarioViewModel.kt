package com.example.leadtech_mobile.viewModel

import androidx.lifecycle.ViewModel
import com.example.leadtech_mobile.model.Usuario
import okhttp3.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import android.os.Handler
import android.os.Looper
import android.util.Log

class UsuarioViewModel : ViewModel() {
    val BASE_URL = "https://leadtech-mobile-default-rtdb.firebaseio.com"
    val client = OkHttpClient()
    val gson = Gson()
    private val handler = Handler(Looper.getMainLooper())

    fun login(email: String, password: String, callback: LoginCallback) {
        val request = Request.Builder()
            .url("$BASE_URL/usuarios.json")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("UsuarioViewModel", "Falha na requisição de login: ${e.message}")
                handler.post { callback.onFailure() }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("UsuarioViewModel", "Resposta do servidor: $body")

                try {
                    val typeToken = object : TypeToken<Map<String, Map<String, Any>>>() {}.type
                    val usuariosMap: Map<String, Map<String, Any>> = gson.fromJson(body, typeToken)

                    val usuario = usuariosMap.values.find {
                        it["email"] == email && it["senha"] == password
                    }

                    if (usuario != null) {
                        Log.d("UsuarioViewModel", "Usuário encontrado: $usuario")
                        handler.post { callback.onSuccess() }
                    } else {
                        Log.d("UsuarioViewModel", "Usuário não encontrado.")
                        handler.post { callback.onFailure() }
                    }
                } catch (e: Exception) {
                    Log.e("UsuarioViewModel", "Erro ao processar a resposta: ${e.message}")
                    handler.post { callback.onFailure() }
                }
            }
        })
    }

    fun cadastrarUsuario(usuario: Usuario, callback: CadastroCallback) {
        val usuarioComId = if (usuario.id.isEmpty()) {
            usuario.copy(id = gerarIdUnico())
        } else {
            usuario
        }

        val usuarioJson = gson.toJson(usuarioComId)
        val requestBody = usuarioJson.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("$BASE_URL/usuarios/${usuarioComId.id}.json")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("UsuarioViewModel", "Falha ao cadastrar usuário: ${e.message}")
                handler.post { callback.onFailure() }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("UsuarioViewModel", "Usuário cadastrado com sucesso.")
                    handler.post { callback.onSuccess() }
                } else {
                    Log.e("UsuarioViewModel", "Falha ao cadastrar usuário. Código de resposta: ${response.code}")
                    handler.post { callback.onFailure() }
                }
            }
        })
    }

    private fun gerarIdUnico(): String {
        return "user_${System.currentTimeMillis()}"
    }
}

interface LoginCallback {
    fun onSuccess()
    fun onFailure()
}

interface CadastroCallback {
    fun onSuccess()
    fun onFailure()
}
