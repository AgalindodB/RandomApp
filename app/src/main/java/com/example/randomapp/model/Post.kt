/**
 * Todas las clases almacenadas en el paquete "Model" del proyecto, estan guardadas aqui ya que la
 * razon es qu contiene CLASES DE DATOS; es decir, son estructuras puras, sin logica alguna.
 * Utilizadas por toda la app ("ViewModel", "Repository", "UI", etc...)
 */
package com.example.randomapp.model

/**
 * La clase "Post" es una DATA CLASS de Kotlin que representa la estructura de un post o entrada
 * obtenida a partir de la solicitud realizada alAPI REST utlizada. Resumiendo, define como se
 * muestran los datos al enviarlos o recibirlos al interactuar con la API.
 *
 * Atributos:
 *
 * "userID" --> Identificador del usuario que crea el post. En esta app, se fija manualmente al
 * valor 1. Variable de tipo "Int".
 *
 * "id" --> Identificador del post. Es asignado automaticamente por la API cuando se crea un nuevo
 * post. Variable de tipo "Int"
 *
 * "title" --> Titulo del post. Campo requerido en el formulario. Variable de tipo "String"
 *
 * "body" --> Cuerpo o contenido completo del post. También se introduce en el formulario.
 *
 * También mencionar que al ser una data class, en Kotlin significa que automáticamente se generan
 * métodos útiles como:
 *
 * --> equals() para comparar instancias
 *
 * --> hashCode()
 *
 * --> toString()
 *
 * --> copy() para duplicar objetos con cambios
 *
 * Se usa como modelo de datos en Retrofit, lo que permite que se pueda serializar y deserializar
 * fácilmente desde/para variable de tipo JSON usando "GsonConverterFactory".
 */
data class Post(
    val userId: Int,
    val id: Int = 0, // la API lo asigna automáticamente
    val title: String,
    val body: String
)

