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
    var idade: Int
):Parcelable
    {
}