package com.franb.notspotify.shared

import androidx.lifecycle.ViewModel
import com.franb.notspotify.Cancion
import com.franb.notspotify.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CancionViewModel: ViewModel(){
    private val _playing = MutableStateFlow(false)
    val playing = _playing.asStateFlow()

    fun cambiarPlaying(nuevoValor : Boolean){
        _playing.value = nuevoValor
    }
    fun cambiarPlaying(){
        _playing.value = !_playing.value
    }

    private val _cancion = MutableStateFlow(Cancion("Título de la cancion", R.drawable.smt4, R.raw.smt4_tokyo, null))
    val cancion = _cancion.asStateFlow()

    fun cambiarCancion(nuevaCancion: Cancion){
        _cancion.value = nuevaCancion
        // TODO duracion
    }

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    fun cambiarLooping(){
        _looping.value = !_looping.value
    }

    private val _tiempoActual = MutableStateFlow(0)
    val tiempoActual = _tiempoActual.asStateFlow()

    private val _duracion = MutableStateFlow(0)
    val duracion = _duracion.asStateFlow()

    private val _shuffle = MutableStateFlow(false)
    val shuffle = _shuffle.asStateFlow()

    fun cambiarShuffle(){
        _shuffle.value = !_shuffle.value
    }
}