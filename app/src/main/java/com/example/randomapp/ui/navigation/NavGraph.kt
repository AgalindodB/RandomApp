/**
 * Las clases almacenadas en el paquete "navigation" son utlizadas para definir nuestro "NavHost",
 * es decir, definir las rutas y como navegar ebtre las diferentes pantalas de la app.
 */
package com.example.randomapp.ui.navigation

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomapp.ui.screens.PostDetailScreen
import com.example.randomapp.ui.screens.PostListScreen
import com.example.randomapp.viewmodel.PostDetailViewModel


/**
 * La clase "NavGraph.kt" define el sistema de rutas de la app. Es donde indicamos que pantallas
 * existen, cómo se llaman sus rutas, y qué ocurre cuando navegamos entre ellas.
 *
 * En ella utilizamos:
 *
 * --> NavHost → Contenedor de navegación.
 *
 * --> composable(...) → Definir cada pantalla.
 *
 * --> NavController → Componente que gestiona el historial de navegación.
 *
 * Con el obejto "Routes" centralizamos los nombres de rutas de navegación, evitamos errores por
 * datos de tipo Strings mal escritos y se usa para llamar al componente navigate(...) y definir
 * composable(...).
 *
 * Con "AppNavGraph" definimos el flujo de navegación y uamos NavHost para establecer un punto de
 * entrada (startDestination) y cada composable() representa una pantalla de la app.
 */
object Routes {
    const val PostList = "post_list"
    const val PostDetail = "post_detail"
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.PostList,
        modifier = modifier
    ) {
        composable(Routes.PostList) {
            PostListScreen(
                onPostClick = { postId ->
                    navController.navigate("${Routes.PostDetail}/$postId")
                }
            )
        }

        composable("${Routes.PostDetail}/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull()
            if (postId != null) {
                PostDetailScreen(
                    postId = postId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}


