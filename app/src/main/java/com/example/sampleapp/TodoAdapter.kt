package com.example.sampleapp

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.ItemTodoBinding
import kotlinx.android.synthetic.main.item_todo.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.ViewHolder> () {
    private var todos: List<Todo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    inner class ViewHolder(val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo) {
            binding.todo = todo
        }
    }
    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }


}