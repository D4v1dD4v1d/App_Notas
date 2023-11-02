package com.brian_david_angel.notas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brian_david_angel.notas.data.DataNotes
import com.brian_david_angel.notas.model.NotasPrincipal
import com.brian_david_angel.notas.ui.theme.NotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Rutas.HomeScreen.ruta){
                        composable(Rutas.HomeScreen.ruta){ NotesAppBar(navController = navigationController) }
                        composable(Rutas.NoteScreen.ruta){ AddNoteBar(navController = navigationController) }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppBar(navController: NavHostController){
    val context = LocalContext.current
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
                    navController.navigate(Rutas.NoteScreen.ruta)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.White)
            }
        },
        content = {
            ListNotas(contentPadding = it)
        }
    )
}

@Composable
fun ListNotas(contentPadding: PaddingValues = PaddingValues(0.dp)){
    val notas: List<NotasPrincipal> = DataNotes.notas
    LazyColumn(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = contentPadding
    ){
        items(notas) {
                nota -> tarjetaNota(nota)
        }
    }
}

@Composable
fun tarjetaNota(notasPrin: NotasPrincipal) {
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
                    text = notasPrin.titulo,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = notasPrin.descripcion,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = notasPrin.fecha,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotasTheme {
        NotesAppBar()
    }
}
 */