/**
 * Las clases almacenadas en el paquete "repository" se debe a que contienen la logica de acceso a
 * datos , para asi poder realizar llamadas a las API, y otras fuentes de datos.
 */
package com.example.randomapp.repository

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import com.example.randomapp.model.Post
import com.example.randomapp.netwok.PostApiService
import javax.inject.Inject

/**
 * La clase denominada "PostRepository" es la encargada de intermediar entre el "ViewModel" y la "API".
 * Su misión es abstraer la logica de acceso a datos externos, en este caso, provenientes de
 * Retrofit, para que el ViewModel no tenga que preocuparse de llamadas HTTP, limitando asi a carga
 * que conlleve, aumentando la toma de datos y la velocidad de respuesta .
 *
 * El patrón "MVVM" recomienda que el "ViewModel" (VM) no tenga conocimiento directo de como se
 * obtienen los datos, solo que puede pedirlos al repositorio.
 * Esto conlleva una serie de ventajas: como la separación de responsabilidades (ya que normalmente
 * suele recaer toda la logica encargada en la API, pero esto simplifica las cosasen el ViewModel),
 * mejor organización y programación para testear el proegrama (pudiendo combinar "Red" utlizando
 * las diferentes formas de consumir APIs, "Cache" como Room o DataStore, utlizando bases de datos
 * simples para pruebas), ademas de un codigo más limpio y reutilizable para el futuro.
 *
 * METODOS UTLIZADOS:
 *
 * "getAllPosts()": Llama al metodo getPosts() de la API, que devuelve una lista de Post. y al ser
 * suspend, debe ejecutarse en una corrutina.
 *
 * "getPostByI(id: INT)": Mediante la llamada realizada a la API, devuelve el post correspondiente
 * al ID dado.
 *
 * "createPost(post: Post)": Mediante una llamada a la API se envia un nuevo post a la API mediante
 * el endpoint "POST" sepueden "almacenar datos", entonces la API devuleve una copia del post con un
 * ID simulado, ya que no se pueden guardar datos en la base de datos.
 */
class PostRepository @Inject constructor(private val api: PostApiService) {

    suspend fun getAllPosts(): List<Post> = api.getPosts()

    suspend fun getPostById(id: Int): Post = api.getPostById(id)

    suspend fun createPost(post: Post): Post = api.createPost(post)
}


