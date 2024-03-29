package com.franb.notspotify.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.franb.notspotify.shared.CancionViewModel
import com.franb.notspotify.shared.MilisAString
import com.franb.notspotify.ui.theme.NotSpotifyTheme

@Composable
fun ComponenteCancion(navController: NavHostController) {
    val contexto = LocalContext.current
    val viewModel: CancionViewModel = viewModel()

    val duracion by viewModel.duracion.collectAsStateWithLifecycle()
    val posicion by viewModel.tiempoActual.collectAsStateWithLifecycle()
    val playing by viewModel.playing.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.CrearExoPlayer(contexto)
        viewModel.SonarMusica(contexto)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TITULO E IMAGEN
        Text(text = "Now playing")
        Text(text = viewModel.cancion.value.nombre)
        Image(
            painter = painterResource(id = viewModel.cancion.value.imagen),
            contentDescription = null,
            //modifier = Modifier.fillMaxSize()
        )

        // CONTROLES
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Slider(
                value = posicion.toFloat(),
                valueRange = 0f..duracion.toFloat(),
                modifier = Modifier.padding(horizontal = 10.dp),
                onValueChange = { viewModel.seek(it.toLong()) }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = MilisAString(posicion), modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = MilisAString(duracion), modifier = Modifier.padding(horizontal = 10.dp))
            }

            // anterior, siguiente, play/stop, loop, shuffle
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // loop
                IconButton(onClick = {
                    viewModel.CambiarLooping()
                }) {
                    Icon(Icons.Rounded.Repeat, "Repetir")
                }

                // anterior
                IconButton(onClick = {
                    viewModel.CancionAnterior() // cambia el valor de cancion
                    viewModel.CargarCancion(contexto) // carga la nueva cancion en el exoplayer
                }) {
                    Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "Cancion anterior")
                }
                //test

                // play pause
                IconButton(onClick = {
                    viewModel.CambiarPlaying()
                }) {
                    if (playing) {
                        Icon(Icons.Rounded.Pause, "Pausar")
                    } else {
                        Icon(Icons.Rounded.PlayArrow, "Play")
                    }
                }

                // siguiente
                IconButton(onClick = {
                    viewModel.CancionSiguiente()
                    viewModel.CargarCancion(contexto)
                }) {
                    Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "Cancion siguiente")
                }

                // shuffle
                IconButton(onClick = { viewModel.CambiarShuffle() }) {
                    Icon(Icons.Rounded.Shuffle, "Shuffle")
                }
            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCancion() {
    NotSpotifyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ComponenteCancion(rememberNavController())
        }
    }
}