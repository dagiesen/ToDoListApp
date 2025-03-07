package com.example.easylist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

import com.example.easylist.data.entities.ItemList

@Dao
interface ItemListDao {

    @Insert
    suspend fun insertItemList(itemList: ItemList)

    @Delete
    suspend fun deleteItemList(itemList: ItemList)

    @Update
    suspend fun updateItemList(itemList: ItemList)


    //Alle Listen abrufen
    @Query("SELECT * FROM ItemList")
    fun getAllItemLists(): Flow<List<ItemList>>

    @Query("SELECT * FROM ItemList WHERE id = :id")
    suspend fun getItemListById(id: Long): ItemList?

    //angeklickte Listen aktualisieren
    @Query("UPDATE ItemList SET isClickedList =:isClickedList WHERE id = :id")
    suspend fun updateListClickStatus(id: Int, isClickedList: Boolean)

    //angeklickte Listen löschen
    @Query("DELETE FROM ItemList WHERE isClickedList = 1")
    suspend fun deleteClickedItemLists()
}