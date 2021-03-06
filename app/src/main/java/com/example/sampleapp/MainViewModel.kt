package com.example.sampleapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo
import kotlinx.coroutines.coroutineScope

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)
    private val todos = repository.getAll()
    private val movies = repository.getMovieData()

    fun insert(todo: Todo){
        repository.insert(todo)
    }
    fun delete(todo: Todo){
        repository.delete(todo)
    }
    fun deleteById(id:Int){
        repository.deleteById(id)
    }
    fun update(todo: Todo){
        repository.update(todo)
    }
    fun updateAll(todoList: List<Todo>) {
        repository.updateAll(todoList)
    }
    fun getMovieData():LiveData<List<Movie>>{
        return movies
    }
    fun getTodoData() : LiveData<List<Todo>>{
        return todos
    }
    fun getMaxOrder() : Int{
        return repository.getMaxOrder()
    }
}