package com.example.sampleapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.sampleapp.ItemMoveCallback
import com.example.sampleapp.MainViewModel
import com.example.sampleapp.R
import com.example.sampleapp.adpater.TodoAdapter
import com.example.sampleapp.databinding.FragmentSecondBinding
import com.example.sampleapp.room.Todo
import kotlinx.coroutines.*


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    val viewModel: MainViewModel by viewModels()
    private lateinit var adapter:TodoAdapter
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

    private fun setRecyclerView(){
        adapter = TodoAdapter(activity!!.application)
        val touchHelper = ItemTouchHelper(ItemMoveCallback(adapter))
        touchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.setHasFixedSize(true)
    }


    /*
    override fun onPause() {
        super.onPause()
        viewModel.viewModelScope.launch(Dispatchers.IO){
            if(adapter.getList().size>0) {
                Log.d("pause","call")
                viewModel.updateAll(adapter.getList())
            }
            if(adapter.getDeletedItemList().size > 0){
                val deletedItemList = adapter.getDeletedItemList()
                for(i in (deletedItemList.size-1) downTo 0)
                    viewModel.delete(deletedItemList[i])
            }
        }
    }*/
}