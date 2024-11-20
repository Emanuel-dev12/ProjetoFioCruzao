package com.example.projetofiocruz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paciente_table")
data class Paciente(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nome: String,
    var idade: Int,
    var local: String
) {
}