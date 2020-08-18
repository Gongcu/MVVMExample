package com.example.sampleapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sampleapp.adpater.MovieAdapter
import com.example.sampleapp.databinding.FragmentFirstBinding
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.view.AddActivity
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFirstBinding>(inflater, R.layout.fragment_first,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setRecyclerView()

        binding.mainButton.setOnClickListener {
            val intent = Intent(activity, AddActivity::class.java)
            startActivity(intent)
        }

    }
    private fun deleteDialog(todo: Movie) {
        val builder = AlertDialog.Builder(this.context!!)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("편집") { _, _ ->
                val intent = Intent(activity, AddActivity::class.java)
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