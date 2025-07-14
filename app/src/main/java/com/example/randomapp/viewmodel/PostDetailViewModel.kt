/**
 * Las clases almacenadas en el paquete "viewmodel" exponen el estado de la UI usando componentes
 * como StateFlow (utlizado para representar un flujo de datos reactivo, es decir datos constantes.
 * No depende del framework de Android por lo que es muchomas flexible y compatible), LiveData
 * (usado para observar datos de forma lifecycle-aware, solo si la UI esta activa) o mutableStateOf
 * (utlizado para datos que debe de sobrevivir a cambios).
 */
package com.example.randomapp.viewmodel

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomapp.model.Post
import com.example.randomapp.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * "PostDetailViewModel" es un ViewModel especifico que gestiona el estado y la logica para la
 * pantalla que muestra el detalle de un post seleccionado por el usuario. Controla la obtencion de
 * datos desde la API para un postId concreto que se le asigna a cada post y asi expone el post a la
 * UI mediante "mutableStateOf". Tambien se manejan errores de red o de ID no encontrado y se ha
 * definido un metodo auxiliar setPost() para cargar un post creado por el usuario.
 *
 * "loadPost(postId: Int)": metodo que llama al repositorio para obtener el post desde la API
 * (GET /posts/{id}). Este se ejecuta dentro de una corrutina (usando viewModelScope.launch),
 * haciedno que se actualiza el post o mostrar un error.
 *
 * "setPost(post: Post)": Metodo que permite agregar manualmente un post existente desde memoria
 * local, pero estos post recien creados no se acaban guardando ya que no disonen de acceso a la
 * base de datos de la API, por lo que no se realiza ninguna llamada de red, donde utlizamos el
 * "postId" que pertenece a un post creado en tiempo real.
 */
@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    var post by mutableStateOf<Post?>(null)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun loadPost(postId: Int) {
        viewModelScope.launch {
            try {
                post = repository.getPostById(postId)
                error = null
            } catch (e: Exception) {
                error = "Error al obtener el post: ${e.message}"
            }
        }
    }
}

