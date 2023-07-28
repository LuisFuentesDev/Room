package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TareaDAO {
    @Insert
    suspend fun insertarTarea(tarea:Tarea)

    @Query("SELECT*FROM tabla_tareas order by id asc")

    fun getTarea():LiveData<List<Tarea>>
}