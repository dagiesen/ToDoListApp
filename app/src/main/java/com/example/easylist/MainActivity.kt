package com.example.easylist


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.easylist.data.AppDatabase
import com.example.easylist.viewmodel.ItemViewModel
import com.example.easylist.viewmodel.ItemViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val viewModel: ItemViewModel by viewModels { ItemViewModelFactory(database) }

        setContent {
            ItemListScreen(viewModel)
        }
    }
}