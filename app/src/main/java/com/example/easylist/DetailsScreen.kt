package com.example.easylist
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easylist.viewmodel.EasyListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: EasyListViewModel,
    navController: NavController,
    itemListId: Long
){
    viewModel.selectList(itemListId.toInt())
    var showDialog by remember {mutableStateOf(false)}
    val itemsInList by viewModel.itemsForSelectedList.collectAsState()
    val itemListName by viewModel.getShoppingListById(itemListId).collectAsState(initial = "Unbekannt")
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
                        viewModel.addShoppingItem(listItem, itemListId.toInt())
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
                    Text("$itemListName",
                        style = MaterialTheme.typography.titleLarge)
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,

                ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = {viewModel.deleteClickedShoppingItem()}
                    ){
                        Text("Ausgewählte Löschen")
                    }

                }

            }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        //.clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, Color.LightGray))
                ){
                    Checkbox(
                        checked = item.isClickedItem,
                        onCheckedChange = {
                            isChecked -> viewModel.toggleItemClicked(item)
                        }
                    )
                    Text(
                        text = item.name,
                        style = TextStyle(
                            textDecoration = if (item.isClickedItem) TextDecoration.LineThrough
                            else TextDecoration.None
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .selectable(selected = false,
                                onClick = {viewModel.toggleItemClicked(item)})
                    )


                }


            }


        }

    }




}

