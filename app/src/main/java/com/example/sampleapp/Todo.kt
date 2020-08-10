package com.example.sampleapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(var title: String) { //data class의 경우 getter,setter,toString의 정의가 필요 없다.
    //autoGenerate는 ID 값을 자동으로 할당해줌
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}