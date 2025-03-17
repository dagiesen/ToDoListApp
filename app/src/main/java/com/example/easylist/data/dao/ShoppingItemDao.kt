package com.example.easylist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.easylist.data.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Update
    suspend fun update(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM ShoppingItem WHERE listId = :listId")
    fun getItemsForList(listId: Int): Flow<List<ShoppingItem>>

    @Query("UPDATE ShoppingItem SET isClickedItem = :isClicked WHERE id = :itemId")
    suspend fun updateItemClickStatus(itemId: Int, isClicked: Boolean)

    @Query("DELETE FROM ShoppingItem WHERE isClickedItem = 1")
    suspend fun deleteClickedItem()
}