package com.example.easylist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isClickedList: Boolean = false
)
