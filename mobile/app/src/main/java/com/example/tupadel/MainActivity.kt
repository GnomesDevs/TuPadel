/*
* TuPadel - Proyecto de android
*
* Fecha de creación: 18/10/2026
* Fecha ultima modificación: 18/10/2026
* Modificado por: Alejandro Martinez
* Nuevas Implementaciones:
* 1- Preview en diferentes tamaños de pantallas y configuraciones
* 2- Vista previa en modo oscuro
* 3- composable: es una anotación de Kotlin que le indica al compilador que esta función no devuelve
* un valor convencional, sino que dibuja un elemento en la interfaz de usuario
*
* */



package com.example.tupadel

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.tupadel.ui.theme.TuPadelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TuPadelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
/* composable es una función de Jetpack Compose en Kotlin
 Es la unidad básica con la que se construyen las pantallas en las aplicaciones Android modernas*/
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hola TuPadel!",
        modifier = modifier
    )
}
/* Esto es para ver la vista previa en diferentes pantallas y configuraciones */
// Vista previa estándar
@Preview(name = "Móvil Estándar", showBackground = true, device = Devices.PIXEL_8)
// Vista previa en pantalla pequeña
@Preview(name = "Móvil Pequeño", showBackground = true, device = "spec:width=360dp,height=640dp")
// Vista previa en modo oscuro
@Preview(name = "Modo Oscuro", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    TuPadelTheme {
        Greeting("Hola TuPadel")
    }
}