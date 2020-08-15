package com.example.sampleapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun BindData(recyclerView: RecyclerView, todos: List<Todo>?){
        val adapter = recyclerView.adapter as TodoAdapter
        adapter.submitList(todos)
    }
}