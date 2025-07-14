/**
 * Aqui se almacnan los @Composables de las pantallas, es decir los elementos visaules y la
 * organización y posicion que llevan en la pantalla, Cada pantalla consume un "ViewModel", muestra
 * su estado ("LazyColumn", formularios, botones, etc...), y llama a los metodos definidos en
 * "ViewModel".
 */
package com.example.randomapp.ui.screens

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomapp.viewmodel.PostDetailViewModel


/**
 * "PostDetailScreen" es una clase que utliza "Composable" que representa la pantalla de los
 * detalles de un post selecionado por e usuario. Esta se muestra al hacer clic sobre un post desde
 * la lista que se carga en la pantalla.
 *
 * La función de "PostDetailScreen" es consultar y mostrar el post completo a partir de su id,
 * mostrar errores si el post no existe o hay problemas de red, y permitir al usuario volver a la
 * pagina rpincipal con un botón.
 *
 * PARAMETROS UTLIZADOS:
 *
 * "postId"	De tipo "Int", es el ID del post seleccionado que se debe mostrar.
 * "onBack"	--> {() -> Unit}, tiene la funcion que se ejecuta al pulsar el botón de “Volver”.
 * "viewModel" --> "PostDetailViewModel", se agrega mediante Hilt; que contiene el estado del post y
 * la logica para cargar la información.
 */
@Composable
fun PostDetailScreen(
    postId: Int,
    onBack: () -> Unit,
    viewModel: PostDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    val post = viewModel.post
    val error = viewModel.error

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Button(onClick = onBack) {
            Text("← Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        post?.let {
            Text(text = it.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.body)
        }
    }
}



