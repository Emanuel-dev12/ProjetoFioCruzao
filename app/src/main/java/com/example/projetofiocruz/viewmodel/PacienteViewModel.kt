package com.example.projetofiocruz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetofiocruz.data.PacienteDatabase
import com.example.projetofiocruz.repositorio.PacienteRepositorio
import com.example.projetofiocruz.model.Paciente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PacienteViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Paciente>>
    private val repositorio: PacienteRepositorio

    init {
        val pacienteDao = PacienteDatabase.getDatabase(application).pacienteDao()
        repositorio = PacienteRepositorio(pacienteDao)
        readAllData = repositorio.readAllData
    }

    fun addPaciente(paciente: Paciente){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.addPaciente(paciente)
        }
    }

    fun updatePaciente(paciente: Paciente){
            viewModelScope.launch(Dispatchers.IO) {
                repositorio.updatePaciente(paciente)
            }
    }

    fun deletePaciente(paciente: Paciente){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.deletePaciente(paciente)
        }
    }

    fun deleteAllPaciente(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.deleteAllPaciente()
        }
    }

}