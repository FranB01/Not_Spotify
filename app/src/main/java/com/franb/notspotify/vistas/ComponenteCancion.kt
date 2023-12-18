package com.franb.notspotify.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.franb.notspotify.Cancion
import com.franb.notspotify.shared.CancionViewModel

@Composable
fun ComponenteCancion(){
    val contexto = LocalContext.current

    val viewModel: CancionViewModel = viewModel()
    val cancion = Cancion("Ejemplo", 0,0)

    Column {
        Text(text = "Now playing")
        Text(text = cancion.nombre)
        Image(painter = painterResource(id = cancion.imagen), contentDescription = null)
        Slider(value = 0f, onValueChange = {}) // todo
        Text(text = "0:00 / 2:53") // todo

        Row {
            // anterior, siguiente, play/stop, loop, shuffle
            // loop
            Button(onClick = { /*TODO*/ }) {
                // TODO if looping icono relleno else icono normal
                Icon(Icons.Rounded.Repeat, "Repetir")
            }

            // anterior
            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "Cancion anterior")
            }

            // play pause
            Button(onClick = { /*TODO*/ }) {
                if (viewModel.playing.value){
                    Icon(Icons.Rounded.PlayArrow, "Play")
                } else {
                    Icon(Icons.Rounded.Pause, "Pausar")
                }
            }

            // siguiente
            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "Cancion siguiente")
            }

            // shuffle
            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Shuffle, "Shuffle")
            }
        }

    }
}