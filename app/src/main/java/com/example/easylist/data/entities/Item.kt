package com.example.easylist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val listId: Int, //Fremdschlüssel auf Liste
    val isClicked: Boolean = false
)
