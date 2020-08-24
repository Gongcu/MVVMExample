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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }


    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todo = todo
            //binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩
        }

    }

    override fun onItemMove(fromPosition:Int, toPosition:Int) {
        if(!set) {
            list.addAll(currentList)
            set=true
        }
        //이거는 order만 바꾸어줌
        val temp = list.get(fromPosition).itemOrder
        list.get(fromPosition).itemOrder=list.get(toPosition).itemOrder
        list.get(toPosition).itemOrder = temp

        Collections.sort(list)
        notifyItemMoved(fromPosition,toPosition)
    }
    override fun onItemDismiss(position: Int) {
        viewModel.viewModelScope.launch ( Dispatchers.IO ){
            viewModel.delete(getItem(position))
        }
    }

    override fun itemMoveFinished() {

    }


    companion object TodoDiffUtil : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    fun swap(from: Int, to:Int){
        val fromItem = getItem(from)
        val toItem =getItem(to)
        getItem(from).fieldCopy(toItem.title,toItem.overview,toItem.posterPath)
        getItem(to).fieldCopy(fromItem.title,fromItem.overview,fromItem.posterPath)

    }

}


