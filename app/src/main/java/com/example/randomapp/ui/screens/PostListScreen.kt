/**
 * Aqui se almacnan los @Composables de las pantallas, es decir los elementos visaules y la
 * organización y posicion que llevan en la pantalla, Cada pantalla consume un "ViewModel", muestra
 * su estado ("LazyColumn", formularios, botones, etc...), y llama a los metodos definidos en
 * "ViewModel".
 */
package com.example.randomapp.ui.screens

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomapp.viewmodel.PostViewModel


/**
 * "PostListScreen" es una clase que utliza "Composable" de Jetpack Compose que representa la UI
 * principal de la app. En esta pantalla el usuario puede visualizar una lista de publicaciones
 * (utlizando el componente LazyColumn), rellenar un formulario con título y cuerpo para insertar
 * sus propias publicaciones, como un nuevo post (simulado) en la lista y pulsar sobre otros post
 * que desee visualizar para ver en detalle
 *
 * Al abrir la app, los posts se cargan automaticamente gracias a "PostViewModel", el formulario
 * permite añadir nuevos elementos, dichos nuevos posts se añaden en el ultimo lugar de la lista.
 * Al hacer clic en un post, se muestra la pantalla de detalle con el ID correspondiente.
 */
@Composable
fun PostListScreen(
    onPostClick: (Int) -> Unit,
    viewModel: PostViewModel = hiltViewModel()
) {
    val posts = viewModel.posts
    val error = viewModel.error

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Formulario
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Cuerpo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && body.isNotBlank()) {
                    viewModel.createPost(title, body)
                    title = ""
                    body = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Error
        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // Lista
        LazyColumn {
            items(posts) { post ->
                Text(
                    text = post.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable { onPostClick(post.id) }
                )
                Divider()
            }
        }
    }
}


