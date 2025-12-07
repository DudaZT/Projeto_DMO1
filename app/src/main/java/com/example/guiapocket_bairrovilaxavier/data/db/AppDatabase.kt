package com.example.guiapocket_bairrovilaxavier.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guiapocket_bairrovilaxavier.data.dao.ServicoDao
import com.example.guiapocket_bairrovilaxavier.model.Servico

@Database(entities = [Servico::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun servicoDao(): ServicoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "guia_pocket.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}