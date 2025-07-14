package com.example.randomapp

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * En Android la clase "Application" es el punto de entrada global de toda aplicacion (no es la
 * norma, pero suele ser utliada en fases tempranas de cualquier porgrama desarrollado para Android).
 * Esta se suele crear antes de cualquier "Activity", "Service" o "BroadcastReciever" y se mantiene
 * asi con vida durante toda la vida util de la app
 *
 * Esta clase la hemos creado porque cuando usamos "Hilt", necesitamos que hilt inyecte dependencias
 * desde el inicio de la aplicacion, y para ello necesitamos una clase que herede de "Application" y
 * definirla con "HiltAndroidApp"
 *
 * ¿Que funcon realiza HiltAndroidApp?:
 * Permite que se genere un componente de "HiltAndroidApp", activando asi el uso global de
 * independencias, aemas de asisitir en otro archivos, como puede ser "ViewModels" (@HiltViewModel),
 * "Repositories" (@Inject) o "Activities" y "Composbales" (@AndroidEntryPoint). Ademas ha de
 * enlazarse con el archivo "AndroidManifest.xml" para indicar que la clase "RandomApp" es una
 * Application.
 *
 * Sin todos estos puntos anteriores, "Hilt" sería incapaz de funcionar y al ejecutarse el programa
 * ocurririan errores en nuestra App como este en especifico (sacado del log de "Build"):
 * "java.lang.IllegalStateException: Hilt is not properly initialized."
 */
@HiltAndroidApp
class RandomApp : Application()



