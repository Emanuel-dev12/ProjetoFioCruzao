package com.example.projetofiocruz.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.projetofiocruz.model.Paciente

@Dao
interface PacienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPaciente(paciente: Paciente)

    @Update
    suspend fun updatePaciente(paciente: Paciente)

    @Query("Select * FROM paciente_table ORDER BY id ASC")
    fun readAllPaciente(): LiveData<List<Paciente>>

    @Delete
    suspend fun deletePaciente(paciente: Paciente)

    @Query("Delete From paciente_table")
    suspend fun deleteAllPacientes()

}