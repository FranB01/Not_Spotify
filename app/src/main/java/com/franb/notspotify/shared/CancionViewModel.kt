package com.franb.notspotify.shared

import androidx.lifecycle.ViewModel
import com.franb.notspotify.Cancion
import com.franb.notspotify.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CancionViewModel: ViewModel(){
    private val _playing = MutableStateFlow(false)
    val playing = _playing.asStateFlow()

    fun actualizarPlaying(nuevoValor : Boolean){
        _playing.value = nuevoValor
    }

    private val _cancion = MutableStateFlow(Cancion("Título de la cancion", R.drawable.smt4,0))
    val cancion = _cancion.asStateFlow()

    private val _tiempoActual = MutableStateFlow(0)
    val tiempoActual = _tiempoActual.asStateFlow()

    private val _duracion = MutableStateFlow(0)
    val duracion = _duracion.asStateFlow()


}