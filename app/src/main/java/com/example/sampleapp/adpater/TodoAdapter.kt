package com.example.sampleapp.adpater


import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.MainViewModel
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemTodoBinding
import com.example.sampleapp.room.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoAdapter(application: Application): ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffUtil), ItemTouchHelperListener {
    private val viewModel = MainViewModel(application)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemTodoBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_todo
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }


    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todo = todo
            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩
        }

    }

    override fun onItemMove(startPos:Int, endPos:Int) {
        if(startPos!=RecyclerView.NO_POSITION &&endPos!=RecyclerView.NO_POSITION  ) {
            viewModel.viewModelScope.launch(Dispatchers.IO) {
            val temp = getItem(startPos).wordOrder
            getItem(startPos).wordOrder = getItem(endPos).wordOrder
            getItem(endPos).wordOrder = temp
                viewModel.itemSwap(getItem(startPos), getItem(endPos))
            }
        }
    }
    override fun onItemDismiss(position: Int) {
        viewModel.viewModelScope.launch ( Dispatchers.IO ){
            viewModel.delete(getItem(position))
        }
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

}