package com.example.sampleapp.adpater

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.adpater.TodoAdapter
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo

object RecyclerViewBindingAdapter {
    @BindingAdapter("movieData")
    @JvmStatic
    fun bindMovieData(recyclerView: RecyclerView, movies: List<Movie>?){
        val adapter = recyclerView.adapter as MovieAdapter
        adapter.submitList(movies)
    }

    @BindingAdapter("todoData")
    @JvmStatic
    fun bindTodoData(recyclerView: RecyclerView, todos: List<Todo>?){
        val adapter = recyclerView.adapter as TodoAdapter
        adapter.submitList(todos)
    }
}