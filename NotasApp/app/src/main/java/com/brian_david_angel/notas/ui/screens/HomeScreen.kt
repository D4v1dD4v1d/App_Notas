package com.brian_david_angel.notas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brian_david_angel.notas.AppViewModelProvider
import com.brian_david_angel.notas.R
import com.brian_david_angel.notas.data.Item
import com.brian_david_angel.notas.ui.theme.NotasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(viewModel: MainModel = viewModel(factory = AppViewModelProvider.Factory), navController: NavController){
    //val context = LocalContext.current
    val homeUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = { Text("Notas", color = Color.White) },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Buscar", tint = Color.White)
                    }

                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.notification), contentDescription = "Recordatorio", tint = Color.White)
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addnote")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.White)
            }
        },
        content = {
            ListNotas(contentPadding = it, itemList = homeUiState.itemList)
        }
    )
}

@Composable
fun ListNotas(contentPadding: PaddingValues = PaddingValues(0.dp), itemList: List<Item>){
    val itemList: List<Item> =  itemList
    if (itemList.isEmpty()) {
        Text(
            text = "No hay ninguna nota agregada :(",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    } else {
        LazyColumn(
            modifier = Modifier.padding(top = 12.dp),
            contentPadding = contentPadding
        ){
            items(items = itemList, key = { it.id }) {
                    item -> tarjetaNota(item)
            }
        }
    }
}

@Composable
fun tarjetaNota(item: Item) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.titulo,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = item.contenido,
                    style = MaterialTheme.typography.bodyLarge
                )
                /*Text(
                    text = notasPrin.fecha,
                    style = MaterialTheme.typography.bodyLarge
                )*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotasTheme {

    }
}