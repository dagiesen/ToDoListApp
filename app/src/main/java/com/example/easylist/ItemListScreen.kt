package com.example.easylist


import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.easylist.viewmodel.ItemViewModel
import androidx.compose.runtime.getValue //wichtig für by-delegate
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController


@Composable
fun ItemListScreen(
    viewModel: ItemViewModel,
    navController: NavController) {
    var listName by remember { mutableStateOf("") }
    val lists by viewModel.itemLists.collectAsState()

    Column(modifier = Modifier.padding(16.dp)){
        OutlinedTextField(
            value = listName,
            onValueChange = { listName = it },
            label = { Text("Neuer Listenname")},
            modifier = Modifier.fillMaxWidth()
        )

        Row{
            Button(
                onClick = {
                    if (listName.isNotBlank()){
                        viewModel.addItemList(listName)
                        listName = ""
                    }
                },

                // modifier = Modifier.padding(vertical = 8.dp)
            ){
                Text("Neue Liste anlegen")
            }

            Button(
                onClick = {viewModel.deleteClickedItemLists()},
                // modifier = Modifier.padding(vertical = 8.dp)

            ){
                Text("Ausgewählte Listen löschen")
            }
        }
        LazyColumn {
            items(lists){ listItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()

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
//    val items by viewModel.items.collectAsState()
//    Column{
//        OutlinedTextField(
//            value = text,
//            onValueChange = { newText ->
//                text = newText.trimStart { it == '0'}
//            },
//            label = { Text("Eingabe") }
//        )
//        Button(onClick =  {
//            viewModel.addItemToList(text)
//            text = ""
//        }){
//            Text("Hinzufügen")
//    }
//        Button(onClick = {
//            viewModel.deleteLastItem()}) {
//            Text("Löschen")
//        }
//        Button(onClick = {viewModel.deleteList()}) {
//            Text("Alles Löschen")
//        }
//
//        LazyColumn {
//            items(items) { item ->
//                    Text(
//                        text = item.name,
//                        style = TextStyle(
//                            textDecoration = if (item.isClicked) TextDecoration.LineThrough else TextDecoration.None
//                        ),
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                            .clickable { viewModel.toggleItemClicked(item) }
//                            )
//                }
//        }
//    }
//}

