package com.example.easylist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.easylist.data.entities.Item
import kotlinx.coroutines.flow.Flow



@Dao
interface ItemDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM Item WHERE listId = :listId")
    fun getItemsForList(listId: Int): Flow<List<Item>> //Items für eine bestimmte Liste

    @Query("UPDATE Item SET isClickedItem = :isClicked WHERE id = :itemId")
    suspend fun updateItemClickStatus(itemId: Int, isClicked: Boolean)

    @Query("DELETE FROM Item WHERE isClickedItem = 1")
    suspend fun deleteClickedItem()
}