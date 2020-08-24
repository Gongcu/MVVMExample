package com.example.sampleapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.retrofit.Movie
import com.example.sampleapp.retrofit.MovieService
import com.example.sampleapp.retrofit.RetrofitAPI
import com.example.sampleapp.retrofit.UpComingMovie
import com.example.sampleapp.room.Todo
import com.example.sampleapp.room.TodoDao
import com.example.sampleapp.room.TodoDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class Repository(application: Application) {
    private val todoDatabase: TodoDatabase = TodoDatabase.getInstance(application)!!
    private val todoDao: TodoDao = todoDatabase.todoDao()
    private val todos:LiveData<List<Todo>> = todoDao.getAll()
    private val retrofit: Retrofit = RetrofitAPI.getInstnace()
    private val api = retrofit.create(MovieService::class.java)

    fun getAll(): LiveData<List<Todo>>{
        return todos
    }

    fun getMaxOrder():Int{
        return todoDao.getMaxOrder()
    }

    fun insert(todo: Todo){
        todoDao.insert(todo)
    }
    fun delete(todo: Todo){
        todoDao.delete(todo)
    }
    fun deleteById(id: Int){
        todoDao.deleteById(id)
    }
    fun update(todo: Todo){
        todoDao.update(todo)
    }
    fun updateAll(todoList: List<Todo>){
        todoDao.updateAll(todoList)
    }
    fun getMovieData(): LiveData<List<Movie>> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<List<Movie>>()

        api.getUpcomingMovie().enqueue(object : Callback<UpComingMovie> {
            override fun onResponse(call: Call<UpComingMovie>, response: Response<UpComingMovie>) {
                if(!response.isSuccessful)
                    Log.e("ERROR:", "데이터를 가져오는데 실패했습니다.")
                data.value=response.body()!!.movieList
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<UpComingMovie>, t: Throwable) {
                t.stackTrace
            }
        })
        return data
    }
}

