package com.example.easylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylist.data.AppDatabase
import com.example.easylist.data.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(database: AppDatabase) : ViewModel() {
    private val dao  = database.itemDao()
    val items: StateFlow<List<Item>> = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addItem(name: String){
        viewModelScope.launch{
            dao.insert(Item(name = name))
        }
    }

//    fun deleteItem(item: Item){
//        viewModelScope.launch{
//            dao.delete(item)
//        }
//    }
//
    fun deleteLastItem(){
        viewModelScope.launch{
            val lastItem = items.value.lastOrNull()
            if (lastItem != null){
                dao.delete(lastItem)
            }
        }
    }

    fun deleteList(){
        viewModelScope.launch{
            dao.deleteList()
        }

    }

//    fun updateItems(name: String){
//        viewModelScope.launch{
//            dao.update(Item(name = name))
//        }
//    }

    fun toggleItemClicked(item: Item) {
        viewModelScope.launch {
            dao.update(item.copy(isClicked = !item.isClicked))
        }
    }

}