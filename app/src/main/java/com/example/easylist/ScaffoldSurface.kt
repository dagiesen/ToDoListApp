package com.example.easylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.easylist.viewmodel.ItemViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSurface(
    viewModel : ItemViewModel,
    navController: NavController
){
    var showDialog by remember {mutableStateOf(false)}
    var listName by remember {mutableStateOf("")}
    val lists by viewModel.itemLists.collectAsState()

    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false},
            title = { Text("Neue Einkaufsliste anlegen")},
            text = {
                TextField(
                    value = listName,
                    onValueChange = { listName = it}
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    if (listName.isNotBlank()){
                        viewModel.addItemList(listName)
                        listName = ""
                    }
                }) {
                    Text("Bestätigen")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }){
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
                    Text("Einkaufslisten",
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
                        onClick = {viewModel.deleteClickedItemLists()}
                    ){
                        Text("Ausgewählte Einkaufslisten löschen")
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
            items(lists){ listItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                     //   .clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, Color.LightGray))

                ){
                    Checkbox(
                        checked = listItem.isClickedList,
                        onCheckedChange = {
                                isChecked -> viewModel.toggleItemListClicked(listItem)
                        },
                        //        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = listItem.name,
                        modifier = Modifier
                            .padding(8.dp)
                            .selectable(selected = false,
                                onClick = {navController.navigate("details/${listItem.id}")})



                    )
                }

            }

        }


    }
}