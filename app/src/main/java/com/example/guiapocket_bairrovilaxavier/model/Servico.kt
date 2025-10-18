package com.example.guiapocket_bairrovilaxavier.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Servico(
    val id: Int,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val endereco: String,
    val telefone: String,
    val website: String,
    val imagem: Int
) : Parcelable
