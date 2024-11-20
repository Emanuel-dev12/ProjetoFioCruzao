package com.example.projetofiocruz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetofiocruz.model.Paciente

@Database(entities = [Paciente::class], version = 2, exportSchema = false )
abstract class PacienteDatabase: RoomDatabase() {

    abstract fun pacienteDao(): PacienteDao

    companion object{
        @Volatile
        private var INSTANCE: PacienteDatabase? = null

        fun getDatabase(context: Context): PacienteDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PacienteDatabase::class.java,
                    "paciente_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}