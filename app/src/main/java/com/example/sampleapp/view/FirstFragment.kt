package com.example.sampleapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.sampleapp.ItemMoveCallback
import com.example.sampleapp.MainViewModel
import com.example.sampleapp.NavMainDirections
import com.example.sampleapp.R
import com.example.sampleapp.adpater.MovieAdapter
import com.example.sampleapp.databinding.FragmentFirstBinding
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.room.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        binding = DataBindingUtil.inflate<FragmentFirstBinding>(inflater,
            R.layout.fragment_first,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setRecyclerView()

    }
    private fun addDialog(movie: Movie) {
        val builder = AlertDialog.Builder(this.context!!)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("추가") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO){
                    val todo = Todo(null, movie.title, movie.overview, movie.poster_path, viewModel.getMaxOrder()+1)
                    viewModel.insert(todo)
                }
                val direction: NavDirections = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                findNavController().navigate(direction)
            }.setNeutralButton("상세"){_,_ ->
                val direction: NavDirections = NavMainDirections.actionGlobalDetailFragment(movie.title,movie.overview,movie.adult,movie.poster_path,movie.release_date)
                findNavController().navigate(direction)
            }
        builder.show()
    }

    private fun setRecyclerView(){
        val adapter =
            MovieAdapter({ movie -> addDialog(movie) },
                { movie -> addDialog(movie) })

        binding.recyclerView.adapter=adapter
        binding.recyclerView.setHasFixedSize(true)
    }
}