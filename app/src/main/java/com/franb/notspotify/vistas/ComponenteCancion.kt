package com.franb.notspotify.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.franb.notspotify.Cancion
import com.franb.notspotify.shared.CancionViewModel

@Composable
fun ComponenteCancion(){
    val contexto = LocalContext.current

    val viewModel: CancionViewModel = viewModel()
    var cancion = Cancion("Ejemplo", 0,0)

    Column {
        Text(text = "Now playing")
        Text(text = cancion.nombre)
        Image(painter = painterResource(id = cancion.imagen), contentDescription = null)
        // TODO player


    }
}