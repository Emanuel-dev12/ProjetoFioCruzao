package com.example.projetofiocruz.data

import androidx.lifecycle.LiveData


public class PacienteRepositorio(private val pacienteDao: PacienteDao) {

    val readAllData: LiveData<List<Paciente>> = pacienteDao.readAllPaciente()

    suspend fun addPaciente(paciente:Paciente){
        pacienteDao.addPaciente(paciente)
    }

}
