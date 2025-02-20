package com.example.easylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylist.data.database.AppDatabase
import com.example.easylist.data.entities.Item
import com.example.easylist.data.entities.ItemList

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(database: AppDatabase) : ViewModel() {
    private val itemListDao = database.itemListDao()
    private val itemDao  = database.itemDao()


    //Listen
    val itemLists: StateFlow<List<ItemList>> = itemListDao.getAllItemLists().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Items für eine bestimmte Liste
    fun getItemsForList(listId: Int): Flow<List<Item>> {
        return itemDao.getItemsForList(listId)
    }

    fun addItemToList(name: String, listId: Int){
        viewModelScope.launch{
            itemDao.insert(Item(name = name, listId = listId))
        }
    }


    fun toggleItemClicked(item: Item) {
        viewModelScope.launch {
            itemDao.updateItemClickStatus(item.id, !item.isClicked)
        }
    }

    fun addItemList(name: String){
        viewModelScope.launch{
            itemListDao.insert(ItemList(name = name))
        }
    }

}