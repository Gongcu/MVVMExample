package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.sampleapp.adpater.MovieAdapter
import com.example.sampleapp.adpater.TodoAdapter
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setRecyclerView()

        binding.mainButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(todo: Movie) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("편집") { _, _ ->
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_TODO_TITLE, todo.title)
                //intent.putExtra(AddActivity.EXTRA_TODO_DESC, todo.description)
                //intent.putExtra(AddActivity.EXTRA_TODO_ID, todo.id)
                startActivity(intent)
            }.setNeutralButton("삭제"){_, _ ->
                //lifecycleScope.launch(Dispatchers.IO){viewModel.delete(todo)}
            }
        builder.show()
    }

    private fun setRecyclerView(){
        // Set contactItemClick & contactItemLongClick lambda
        val adapter =
            MovieAdapter({ movie -> deleteDialog(movie) },
                { movie -> deleteDialog(movie) })

        binding.recyclerView.adapter=adapter
        binding.recyclerView.setHasFixedSize(true)
        //viewModel.logMovieData()
    }
}
