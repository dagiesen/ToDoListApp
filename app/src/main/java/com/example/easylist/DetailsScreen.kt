package com.example.easylist
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.easylist.viewmodel.ItemViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: ItemViewModel,
    navController: NavController,
    itemListId: Long
){
    viewModel.selectList(itemListId.toInt())
    var showDialog by remember {mutableStateOf(false)}
    val itemsInList by viewModel.itemsForSelectedList.collectAsState()
   // val itemsInList by viewModel.getItemListById(itemListId).collectAsState()
    var listItem by remember { mutableStateOf("") }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = {Text("Hinzufügen")},
            text = {
                TextField(
                    value = listItem,
                    onValueChange = {listItem = it}
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    if (listItem.isNotBlank()){
                        viewModel.addItemToList(listItem, itemListId.toInt())
                        listItem = ""
                    }
                }) {
                    Text("Bestätigen")
                }

            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text("Abbrechen")
                }

            }
        )
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Die Liste hat die Id $itemListId",
                        style = MaterialTheme.typography.titleLarge)
                }
            )
        },
        bottomBar = {
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {showDialog = true}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(itemsInList){ item ->
                Text(
                    text = item.name
                )
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//
//                ){
//                    Checkbox(
//                        checked = listItem.isClickedList,
//                        onCheckedChange = {
//                                isChecked -> viewModel.toggleItemListClicked(listItem)
//                        },
//                        //        modifier = Modifier.padding(8.dp)
//                    )
//                    Text(
//                        text = listItem.name,
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .selectable(selected = false,
//                                onClick = {navController.navigate("details/${listItem.id}")})
//
//
//
//                    )
//                }

            }


        }

    }




}

//@Composable
//fun DetailsScreen(
//    viewModel: ItemViewModel,
//    navController: NavController,
//    itemListId: Long
//){
//    val item by viewModel.getItemsForList(itemListId.toInt()).collectAsState("")
//    var text by remember { mutableStateOf("") }
//
//
//
//
//    Column(modifier = Modifier) {
//        OutlinedTextField(
//            value = text,
//            onValueChange = { text = it},
//            label = { Text("Eingabe")}
//        )
//        Button (onClick = {
//            viewModel.addItemToList(text, itemListId.toInt())
//            text = ""
//        }){
//            Text("Hinzufügen")
//        }
//
//        LazyColumn {
//            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
//                Text(text = item)
//
//            }
//        }
//
//
//
//    }}

