package com.example.sampleapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    //autoGenerate는 null을 받으면 ID 값을 자동으로 할당해줌
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name="overview")
    var overview: String,

    @ColumnInfo(name="poster_path")
    var posterPath: String,

    @ColumnInfo(name = "word_order")
    var itemOrder: Int
    ) : Comparable<Todo>
{
    constructor(): this(null,"","","",0)
    fun fieldCopy(title: String,overview: String,posterPath: String){
        this.title=title
        this.overview=overview
        this.posterPath=posterPath
    }

    override fun compareTo(other: Todo): Int {
        return this.itemOrder.compareTo(other.itemOrder)
    }
}

