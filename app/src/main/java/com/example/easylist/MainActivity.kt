package com.example.easylist


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easylist.data.database.AppDatabase
import com.example.easylist.viewmodel.EasyListViewModel
import com.example.easylist.viewmodel.EasyListViewModelFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val viewModel: EasyListViewModel by viewModels { EasyListViewModelFactory(database) }

        setContent {
            AppNavHost(viewModel)

        }
    }
}


@Composable
fun AppNavHost(viewModel: EasyListViewModel) {
    val navController = rememberNavController()

    //Navgraph
    NavHost(navController = navController, startDestination = "home"){
        composable ("home") { ScaffoldSurface(viewModel, navController)}
        composable ("details/{itemListId}") { entry ->
            val itemListId = entry.arguments?.getString("itemListId")?.toLongOrNull()
            if (itemListId != null){
                DetailsScreen(viewModel, navController, itemListId)
            } else {
                Text("Fehler")
            }

        }
    }
}



