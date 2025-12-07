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
    suspend fun inserir(servico: Servico)

    @Update
    suspend fun atualizar(servico: Servico)

    @Delete
    suspend fun deletar(servico: Servico)

    @Query("SELECT * FROM servico ORDER BY nome ASC")
    fun listarTodos(): Flow<List<Servico>>

    @Query("SELECT * FROM servico WHERE id = :id")
    suspend fun buscarPorId(id: Int): Servico?

}