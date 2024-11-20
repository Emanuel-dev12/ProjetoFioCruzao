package com.example.projetofiocruz.repositorio

import androidx.lifecycle.LiveData
import com.example.projetofiocruz.data.PacienteDao
import com.example.projetofiocruz.model.Paciente


public class PacienteRepositorio(private val pacienteDao: PacienteDao) {

    val readAllData: LiveData<List<Paciente>> = pacienteDao.readAllPaciente()


    suspend fun addPaciente(paciente: Paciente){
        pacienteDao.addPaciente(paciente)
    }


    suspend fun updatePaciente(paciente: Paciente){
        pacienteDao.updatePaciente(paciente)
    }

    suspend fun deletePaciente(paciente: Paciente){
        pacienteDao.deletePaciente(paciente)
    }

    suspend fun deleteAllPaciente(){
        pacienteDao.deleteAllPacientes()
    }

}
