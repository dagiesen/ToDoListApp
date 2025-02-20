package com.example.easylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.easylist.viewmodel.ItemViewModel
import androidx.compose.runtime.getValue //wichtig für by-delegate
import androidx.compose.foundation.lazy.items //wichtig, wurde nicht als Import angeboten
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun ItemListScreen(viewModel: ItemViewModel){
    var text by remember { mutableStateOf("") }
    val items by viewModel.items.collectAsState()

    Column{
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText.trimStart { it == '0'}
            },
            label = { Text("Eingabe") }
        )

        Button(onClick =  {
            viewModel.addItem(text)
            text = ""

        }){
            Text("Hinzufügen")

    }

        Button(onClick = {
            viewModel.deleteLastItem()}) {
            Text("Löschen")
        }

        Button(onClick = {viewModel.deleteList()}) {
            Text("Alles Löschen")
        }

        LazyColumn {
            items(items) { item ->
                    Text(
                        text = item.name,
                        style = TextStyle(
                            textDecoration = if (item.isClicked) TextDecoration.LineThrough else TextDecoration.None
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { viewModel.toggleItemClicked(item) }
                            )
                }
        }
    }
}

