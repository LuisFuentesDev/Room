package com.example.room

import androidx.lifecycle.LiveData

class Repositorio(private val tareaDao: TareaDAO) {
    suspend fun intertTask(tarea:Tarea){
        tareaDao.insertarTarea(tarea)
    }
    fun getTareas():LiveData<List<Tarea>>{
        return tareaDao.getTarea()
    }
}