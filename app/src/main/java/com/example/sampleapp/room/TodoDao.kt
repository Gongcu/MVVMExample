package com.example.sampleapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampleapp.room.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo ORDER BY word_order ASC")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT MAX(word_order) FROM Todo")
    fun getMaxOrder(): Int

    @Insert
    fun insert(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(todo: List<Todo>)

    @Delete
    fun delete(todo: Todo)

    @Query("DELETE FROM Todo WHERE id=:id")
    fun deleteById(id: Int)
}