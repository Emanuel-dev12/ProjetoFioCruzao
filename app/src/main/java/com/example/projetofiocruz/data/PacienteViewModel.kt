package com.example.projetofiocruz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PacienteViewModel (application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Paciente>>
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

}