package com.franb.notspotify.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.franb.notspotify.Cancion
import com.franb.notspotify.Greeting
import com.franb.notspotify.shared.CancionViewModel
import com.franb.notspotify.ui.theme.NotSpotifyTheme

@Composable
fun ComponenteCancion() {
    val contexto = LocalContext.current

    val viewModel: CancionViewModel = viewModel()

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
        Column{
            Slider(
                value = viewModel.tiempoActual.value.toFloat(),
                valueRange = 0f..viewModel.duracion.value.toFloat(),
                modifier = Modifier.padding(horizontal = 10.dp),
                onValueChange = {
                    /* TODO */
                })
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // TODO
                Text(text = "0:00", modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = "3:26", modifier = Modifier.padding(horizontal = 10.dp))
            }

            // anterior, siguiente, play/stop, loop, shuffle
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                // loop
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    // TODO if looping icono relleno else icono normal
                    Icon(Icons.Rounded.Repeat, "Repetir")
                }

                // anterior
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "Cancion anterior")
                }

                // play pause
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    if (viewModel.playing.value) {
                        Icon(Icons.Rounded.PlayArrow, "Play")
                    } else {
                        Icon(Icons.Rounded.Pause, "Pausar")
                    }
                }

                // siguiente
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "Cancion siguiente")
                }

                // shuffle
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
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
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ComponenteCancion()
        }
    }
}