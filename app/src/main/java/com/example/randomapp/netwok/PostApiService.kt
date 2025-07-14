/**
 * Es el unico paquete que debe de contener la interfaz "Retrofit" para poder consumir la API y
 * poder realizar llamadas de datos a esta.
 *
 */
package com.example.randomapp.netwok

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import com.example.randomapp.model.Post
import retrofit2.http.*

/**
 * "PostApiService" es una interfaz de "Retrofit" que define las operaciones HTTP que la app puede
 * realizar llamadas la API que hemos utlizado: "jsonplaceholder.typicode.com."
 *
 * Es el punto donde Retrofit se conecta con Internet y realiza peticiones "GET" (obtener datos
 * almacenados en la API) y POST (poder subir datos a la base de datos de la API).
 * Cada método de esta interfaz representa una llamada a un endpoint específico de la API, haciedno
 * que se muestren los datos a traves de la pantalla.
 *
 * El "Retrofit" funciona de la siguinte manera: convierte automáticamente cada función en una
 * llamada HTTP cuando se utiliza en el repositorio. Gracias al uso de anotaciones como @GET y
 * @POST, no necesitamos preocuparnos por escribir código HTTP de bajo nivel.
 *
 *
 * METODOS UTLIZADOS EN LA LAS LLAMDAS A LA API:
 *
 * "getPosts": Realiza una petición HTTP-GET (obtener) https://jsonplaceholder.typicode.com/posts.
 * Dicha petición devuelve una lista de objetos Post; y se marca como "suspend", es decir, que debe
 * ejecutarse dentro de una corrutina (viewModelScope.launch, por ejemplo).
 *
 * "getPostById": Realiza una petición HTTP-GET /posts/{id}, por ejemplo: /posts/5. En este ejemplo
 * buscamos el post con el id asignado, siendo el numero 5. Usamos la anotación @Path para inyectar
 * dinamicamente el id en la URL, haciendo que se nos devuelva un solo objeto Post.
 *
 * ""createPost(post: Post): Realiza una peticion HTTP-POST al endpoint /posts.
 *
 * Usa @Body para enviar el objeto Post en formato JSON. La API simula la creación y responde con
 * un Post que contiene un ID ficticio, ya que los datos que introducimos nunca acaban guardandose,
 * ya que se utliza un permiso solo para acceder pero no para guardar datos.
 * Importante: la API no guarda realmente el post (es solo mock), para hacerlo se deberia de utlizar
 * o un almacenamiento local temporal (es decir,una vez que la app se cierra los datos se pierden),
 * o recurrir a opcones mas avanzadas y complejas
 */
interface PostApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

}

