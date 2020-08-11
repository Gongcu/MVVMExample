package com.example.sampleapp

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(application: Application) {
    private val todoDatabase: TodoDatabase = TodoDatabase.getInstance(application)!!
    private val todoDao: TodoDao = todoDatabase.todoDao()
    private val todos:LiveData<List<Todo>> = todoDao.getAll()

    fun getAll(): LiveData<List<Todo>>{
        return todos
    }

    fun insert(todo: Todo){
        todoDao.insert(todo)
    }
    fun delete(todo: Todo){
        todoDao.delete(todo)
    }
}

