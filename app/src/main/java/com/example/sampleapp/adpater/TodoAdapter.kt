package com.example.sampleapp.adpater


import android.app.Application
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.MainViewModel
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemTodoBinding
import com.example.sampleapp.room.Todo
import java.util.*
import kotlin.collections.ArrayList


class TodoAdapter(application: Application): RecyclerView.Adapter<TodoAdapter.ViewHolder>(), ItemTouchHelperListener {
    private var viewModel :MainViewModel = MainViewModel(application)
    private var list = ArrayList<Todo>()
    private var deletedItemList = ArrayList<Todo>()
    var set = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemTodoBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_todo
    }

    fun getList() : ArrayList<Todo> {
        return list
    }
    fun getDeletedItemList() : ArrayList<Todo> {
        return deletedItemList
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = list[position]
        holder.bind(todo)
    }


    class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todo = todo
            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩
        }

    }

    override fun onItemMove(fromPosition:Int, toPosition:Int) {
        val temp = list[fromPosition].itemOrder
        list[fromPosition].itemOrder=list[toPosition].itemOrder
        list[toPosition].itemOrder = temp

        Collections.sort(list)
        notifyItemMoved(fromPosition,toPosition)
    }


    fun setList(list:ArrayList<Todo>){
        this.list=list
        notifyDataSetChanged()
    }


    override fun onItemDismiss(position: Int) {
        deletedItemList.add(list[position])
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


