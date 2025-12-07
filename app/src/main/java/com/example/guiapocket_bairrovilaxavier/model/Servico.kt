package com.example.guiapocket_bairrovilaxavier.model

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "servico")
data class Servico(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val endereco: String,
    val telefone: String,
    val website: String,
    val imagemUri: String
) : Serializable {
    val imagem: Uri
        get() = imagemUri.toUri()
}