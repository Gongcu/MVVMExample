package com.example.sampleapp.adpater

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.adpater.TodoAdapter
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo
import java.util.*
import kotlin.collections.ArrayList

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
        //리사이클러뷰의 어답터를 가져옴
        val adapter = recyclerView.adapter as TodoAdapter
        if(todos!=null)
            adapter.setList(ArrayList(todos))
    }
}