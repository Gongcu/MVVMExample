package com.example.sampleapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.sampleapp.R
import com.example.sampleapp.adpater.TodoAdapter
import com.example.sampleapp.databinding.FragmentSecondBinding
import com.example.sampleapp.room.Todo

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSecondBinding>(inflater,
            R.layout.fragment_second,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setRecyclerView()

    }
    private fun deleteDialog(todo: Todo) {
        val builder = AlertDialog.Builder(this.context!!)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("편집") { _, _ ->
                val intent = Intent(activity, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_TODO_TITLE, todo.title)
                //intent.putExtra(AddActivity.EXTRA_TODO_DESC, todo.description)
                //intent.putExtra(AddActivity.EXTRA_TODO_ID, todo.id)
                startActivity(intent)
            }
        builder.show()
    }

    private fun setRecyclerView(){
        // Set contactItemClick & contactItemLongClick lambda
        val adapter =
            TodoAdapter({ todo -> deleteDialog(todo) },
                { todo -> deleteDialog(todo) })

        binding.recyclerView.adapter=adapter
        binding.recyclerView.setHasFixedSize(true)
        //viewModel.logMovieData()
    }
}