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
import kotlinx.coroutines.*
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList


class TodoAdapter(application: Application): ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffUtil), ItemTouchHelperListener {
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
        val todo = getItem(position)
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
        if(!set) {
            list.addAll(currentList)
            set=true
        }
        //이거는 order만 바꾸어줌
        val temp = list[fromPosition].itemOrder
        list[fromPosition].itemOrder=list[toPosition].itemOrder
        list[toPosition].itemOrder = temp

        Collections.sort(list)
        notifyItemMoved(fromPosition,toPosition)

    }

    override fun onItemMoveFinished() {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.updateAll(list)
        }
    }


    override fun onItemDismiss(position: Int) {
        if (!set) {
            list.addAll(currentList)
            set = true
        }
        /*
        deletedItemList.add(list[position])
        list.removeAt(position)
        Collections.sort(list)
        //notifyItemRemoved(position)
         */
        val index=currentList.indexOf(list[position])
        list.removeAt(position)

        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.delete(getItem(index))
        }
    }


    companion object TodoDiffUtil : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return false
        }
    }
}


