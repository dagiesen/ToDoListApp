package com.example.easylist.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylist.data.database.AppDatabase
import com.example.easylist.data.entities.ShoppingItem
import com.example.easylist.data.entities.ShoppingList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EasyListViewModel(database: AppDatabase) : ViewModel() {
    private val shoppingListDao = database.shoppingListDao()
    private val shoppingItemDao = database.shoppingItemDao()


    //Listen
    val shoppingLists: StateFlow<List<ShoppingList>> = shoppingListDao
        .getAllShoppingLists()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedListId = MutableStateFlow<Int?>(null) // Speichert die aktuelle Liste
    val itemsForSelectedList: StateFlow<List<ShoppingItem>> =
        _selectedListId
            .filterNotNull()
            .flatMapLatest { listId -> getShoppingItem(listId) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun selectList(listId: Int) {
        _selectedListId.value = listId
    }


    //ShoppingItems-Operationen
    private fun getShoppingItem(listId: Int): Flow<List<ShoppingItem>> {
        return shoppingItemDao.getItemsForList(listId)
    }

    fun addShoppingItem(name: String, listId: Int) {
        viewModelScope.launch {
            shoppingItemDao.insert(ShoppingItem(name = name, listId = listId))
        }
    }

    fun toggleItemClicked(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingItemDao.updateItemClickStatus(shoppingItem.id, !shoppingItem.isClickedItem)
        }
    }


    fun deleteClickedShoppingItem() {
        viewModelScope.launch {
            shoppingItemDao.deleteClickedItem()
        }
    }

    //ShoppingLists-Operationen
    fun addShoppingList(name: String) {
        viewModelScope.launch {
            shoppingListDao.insertShoppingList(ShoppingList(name = name))
        }
    }

    fun getShoppingListById(id: Long): Flow<String?> {
        return shoppingListDao.getShoppingListById(id).map { shoppingList -> shoppingList?.name }
    }


    fun toggleShoppingListClicked(shoppingList: ShoppingList) {
        viewModelScope.launch {
            shoppingListDao.toggleShoppingListSelection(shoppingList.id, !shoppingList.isMarkedList)
        }
    }

    fun deleteClickedShoppingLists() {
        viewModelScope.launch {
            shoppingListDao.deleteSelectedShoppingLists()
        }
    }
}