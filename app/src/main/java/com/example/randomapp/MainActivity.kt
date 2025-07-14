package com.example.randomapp

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.randomapp.ui.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint
import com.example.randomapp.ui.theme.RandomAppTheme

/**
 * La clase "MainActivity" es el punto de entrada visual de la app.
 * Es una actividad basada en ComponentActivity con Compose, que se encarga de diferentes funciones
 * como aplicar el tema de la app, crear el NavController para gestionar la navegacion enre
 * pantallas, poder mostrar la UI mediante el componente AppNavGraph y utlizar el componente
 * "Scaffold" para estructurar la interfaz
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomAppTheme {
                val navController = rememberNavController()
                Scaffold { padding -> AppNavGraph(navController, modifier = Modifier.padding(padding)) }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {}