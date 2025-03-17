package com.example.easylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easylist.data.database.AppDatabase

class EasyListViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EasyListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EasyListViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

