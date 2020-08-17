package com.example.sampleapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampleapp.room.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}