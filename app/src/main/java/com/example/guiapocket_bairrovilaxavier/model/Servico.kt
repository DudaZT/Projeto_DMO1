package com.example.guiapocket_bairrovilaxavier.model

import java.io.Serializable

data class Servico(
    val id: Int,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val endereco: String,
    val telefone: String,
    val website: String,
    val imagem: Int
) : Serializable