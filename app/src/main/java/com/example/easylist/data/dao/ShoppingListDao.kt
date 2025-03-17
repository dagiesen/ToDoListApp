package com.example.easylist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.easylist.data.entities.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Insert
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Delete
    suspend fun deleteItemList(shoppingList: ShoppingList)

    @Update
    suspend fun updateItemList(shoppingList: ShoppingList)


    @Query("SELECT * FROM ShoppingList")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingList WHERE id = :id")
    fun getShoppingListById(id: Long): Flow<ShoppingList?>

    @Query("UPDATE ShoppingList SET isMarkedList =:isClickedList WHERE id = :id")
    suspend fun toggleShoppingListSelection(id: Int, isClickedList: Boolean)


    @Query("DELETE FROM ShoppingList WHERE isMarkedList = 1")
    suspend fun deleteSelectedShoppingLists()
}