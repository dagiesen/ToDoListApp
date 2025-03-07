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
import com.example.easylist.viewmodel.ItemViewModel
import com.example.easylist.viewmodel.ItemViewModelFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val viewModel: ItemViewModel by viewModels { ItemViewModelFactory(database) }

        setContent {
            AppNavHost(viewModel)
          //  ScaffoldSurface(viewModel)
        }
    }
}


@Composable
fun AppNavHost(viewModel: ItemViewModel) {
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



@Composable
fun AppNavHostOld(viewModel: ItemViewModel) {
    val navController = rememberNavController()

    //Navgraph
    NavHost(navController = navController, startDestination = "home"){
        composable ("home") { ItemListScreen(viewModel, navController)}
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