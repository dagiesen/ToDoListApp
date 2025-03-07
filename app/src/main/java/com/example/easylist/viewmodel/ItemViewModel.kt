package com.example.easylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylist.data.database.AppDatabase
import com.example.easylist.data.entities.Item
import com.example.easylist.data.entities.ItemList

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(database: AppDatabase) : ViewModel() {
    private val itemListDao = database.itemListDao()
    private val itemDao  = database.itemDao()


    //Listen
    val itemLists: StateFlow<List<ItemList>> = itemListDao.getAllItemLists().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedListId = MutableStateFlow<Int?>(null) // Speichert die aktuelle Liste
    val itemsForSelectedList: StateFlow<List<Item>> = _selectedListId
        .filterNotNull()
        .flatMapLatest { listId -> getItemsForList(listId) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun selectList(listId: Int) {
        _selectedListId.value = listId
    }

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
            itemDao.updateItemClickStatus(item.id, !item.isClickedItem)
        }
    }

    fun addItemList(name: String){
        viewModelScope.launch{
            itemListDao.insertItemList(ItemList(name = name))
        }
    }


    fun getItemListById(id: Long){
        viewModelScope.launch{
            itemDao.getItemsForList(id.toInt())
        }

    }

    fun toggleItemListClicked(itemList: ItemList){
        viewModelScope.launch{
            itemListDao.updateListClickStatus(itemList.id, !itemList.isClickedList)
        }
    }

    fun deleteClickedItemLists(){
        viewModelScope.launch{
            itemListDao.deleteClickedItemLists()
        }
    }

}