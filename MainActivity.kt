package com.ebc.notificacionesdemo

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebc.notificacionesdemo.ui.theme.NotificacionesdemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Notificaciones.crearCanal(this)

        setContent {
            NotificacionesdemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        //LaboratorioNotificaciones()
                        LaboratorioAnimaciones()
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun LaboratorioNotificaciones() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Laboratorio de Notificaciones",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Objetivo: disparar notificación simple, texto amplio e imagen"
        )

        HorizontalDivider()

        Button(
            onClick = { Notificaciones.enviarBasica(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Notificación básica")
        }

        Button(
            onClick = { Notificaciones.enviarTextoLargo(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Notificación expandible")
        }

        Button(
            onClick = { Notificaciones.enviarConImagen(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Notificación Imagen")
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Limpiar notificaciones")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun LaboratorioAnimaciones() {
    var visible by remember {
        mutableStateOf(true)
    }

    var expandido by remember {
        mutableStateOf(true)
    }

    var leido by remember {
        mutableStateOf(true)
    }

    val colorFondo by animateColorAsState(
        targetValue = if (leido) Color(0xFFDCDCDC) else Color(0xFFD4DEFD)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Lab de avisos visuales",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black
        )

        Button(
            onClick = {
                visible = !visible
            }
        ) {
            Text(text = "Ocultar/Mostrar visual")
        }


        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() +
                    slideInVertically(
                        initialOffsetY = { -it }
                    ),

            exit = fadeOut() +
                    slideOutVertically(
                        targetOffsetY = { -it }
                    )


        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),

                colors = CardDefaults.cardColors(
                   containerColor = colorFondo
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Recordatorio de clase",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Tienes una actividad por hacer",
                    )

                    if(expandido) {
                        Text(text = "Recuerda completar tu fase 1 y 2" +
                                "De tu proyecto final... con compose" +
                                "Si usas vistas XML -> pecado!!!" +
                                "esto esta deprecado!!!!")
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextButton(
                            onClick = {
                                expandido = !expandido
                            }
                        ) {
                            Text(
                                text = if (expandido) "Ver menos" else "Ver mas"
                            )
                        }

                        TextButton(
                            onClick = {
                                leido = !leido
                            }
                        ) {
                            Text(
                                text = if (leido) "Marcar no leido" else "Marcar leido"
                            )
                        }
                    }
                }

            }

        }

        /*BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
            )
        }*/


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    NotificacionesdemoTheme {
        Greeting("Android")
    }
}