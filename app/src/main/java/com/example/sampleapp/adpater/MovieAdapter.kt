package com.example.sampleapp.adpater


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemTodoBinding
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo


    class MovieAdapter(val movieItemClick: (Movie) -> Unit, val movieItemLongClick: (Movie) -> Unit):
    ListAdapter<Movie, MovieAdapter.ViewHolder>(
        MovieDiffUtil
    ) {

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


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩

            binding.root.setOnClickListener {
                movieItemClick(movie)
            }
            binding.root.setOnLongClickListener {
                movieItemLongClick(movie)
                true
            }
        }
    }

    companion object MovieDiffUtil: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }
    }


}