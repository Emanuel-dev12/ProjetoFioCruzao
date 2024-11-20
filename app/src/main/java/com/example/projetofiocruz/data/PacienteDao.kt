package com.example.projetofiocruz.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PacienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPaciente(paciente: Paciente)

    @Query("Select * FROM paciente_table ORDER BY id ASC")
    fun readAllPaciente(): LiveData<List<Paciente>>

}