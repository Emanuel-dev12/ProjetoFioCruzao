package com.example.projetofiocruz.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "paciente_table")
data class Paciente(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nome: String,
    var local: String,
    var idade: Int,
    var cpf: String, // CPF como String para preservar os 11 dígitos
    var telefone: String // Número de telefone como String para maior flexibilidade no formato
) : Parcelable