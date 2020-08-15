package com.example.sampleapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.ItemTodoBinding


class TodoAdapter(val todoItemClick: (Todo) -> Unit, val todoItemLongClick: (Todo) -> Unit):
    ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffUtil) {
    //private var todos: List<Todo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTodoBinding>(layoutInflater,viewType,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_todo
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }

    inner class ViewHolder(private val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo) {
            binding.todo = todo
            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩

            binding.root.setOnClickListener {
                todoItemClick(todo)
            }
            binding.root.setOnLongClickListener {
                todoItemLongClick(todo)
                true
            }
        }
    }

    companion object TodoDiffUtil: DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem==newItem
        }
    }


}