package com.example.guiapocket_bairrovilaxavier.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.guiapocket_bairrovilaxavier.model.Servico
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicoDao {
    @Insert
    suspend fun inserir(servico: Servico) // Insere no Room

    @Update
    suspend fun atualizar(servico: Servico) // Atualiza serviço

    @Delete
    suspend fun deletar(servico: Servico) // Exclui serviço

    @Query("SELECT * FROM servico ORDER BY nome ASC") // Flow -> atualiza automaticamente
    fun listarTodos(): Flow<List<Servico>>

    @Query("SELECT * FROM servico WHERE id = :id")
    suspend fun buscarPorId(id: Int): Servico? // Busca por ID pra editar

}