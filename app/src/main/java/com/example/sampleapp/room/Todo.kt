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

    override fun compareTo(other: Todo): Int {
        return this.itemOrder.compareTo(other.itemOrder)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Todo

        if (id != other.id) return false
        if (title != other.title) return false
        if (overview != other.overview) return false
        if (posterPath != other.posterPath) return false
        if (itemOrder != other.itemOrder) return false

        return true
    }
}

