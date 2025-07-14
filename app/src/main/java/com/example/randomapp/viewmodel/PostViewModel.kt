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
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * "PostViewModel" es la clase que representa al "cerebro" de la UI, siguiendo el patron MVVM, y
 * cumpliendo las siguienes funciones: Mantener el estado de la pantalla principal, coordinar la
 * obtención e inserción de posts (nuestros datos), exponer propiedades observables (posts, error)
 * a la UI y utilizar "corrutinas" para llamar al repositorio sin bloquear el hilo principal.
 *
 * Recalcamos que aqui se permite que Hilt cree e inyecte este ViewModel automaticamente. ademas de
 * que requiere que el constructor tenga parametros inyectables (PostRepository).
 *
 * El metodo principal "init {getPosts()}" permite llamar automaticamente a "getPosts()" cuando el
 * ViewModel se crea, cargando asi la lista incial de de posts en la pantalla.
 *
 * "fun createPost(title: String, body: String)": Crea un objeto Post con userId = 1 y los datos
 * introducidos en el formulario.Despues se llama al repositorio para hacer un POST a la API, asi se
 * añade el post al final de la lista (posts = posts + result), actualizandose automaticamente la UI.
 *
 * "fun getLocalPostById(id: Int): Post?": Devuelve un post existente de la lista almacenada, para
 * asi mostrar detalles de un post recien creado a traves del formulario
 *
 */
@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                posts = repository.getAllPosts()
                error = null
            } catch (e: Exception) {
                error = "Error al cargar posts: ${e.message}"
            }
        }
    }

    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            try {
                val newPost = Post(title = title, body = body, userId = 1)
                val result = repository.createPost(newPost)
                posts = posts + result // añade al final
                error = null
            } catch (e: Exception) {
                error = "Error al insertar post: ${e.message}"
            }
        }
    }
}


