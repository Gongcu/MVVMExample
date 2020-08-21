package com.example.sampleapp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sampleapp.Repository
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo

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
    fun getMovieData():LiveData<List<Movie>>{
        return movies
    }
    fun getTodoData() : LiveData<List<Todo>>{
        return todos
    }
}