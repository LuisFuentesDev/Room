package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_tareas")
data class Tarea(val nombre: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}