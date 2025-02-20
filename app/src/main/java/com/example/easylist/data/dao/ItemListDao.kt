package com.example.easylist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import com.example.easylist.data.entities.ItemList

@Dao
interface ItemListDao {

    @Insert
    suspend fun insert(itemList: ItemList)

    @Delete
    suspend fun delete(itemList: ItemList)

    @Query("SELECT * FROM item_lists")
    fun getAllItemLists(): Flow<List<ItemList>>

}