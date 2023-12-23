package com.franb.notspotify.shared

import androidx.lifecycle.ViewModel
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

    private val _tiempoActual = MutableStateFlow(0L)
    val tiempoActual = _tiempoActual.asStateFlow()

    private val _duracion = MutableStateFlow(0L)
    val duracion = _duracion.asStateFlow()

    private val _shuffle = MutableStateFlow(false)
    val shuffle = _shuffle.asStateFlow()

    fun cambiarShuffle(){
        _shuffle.value = !_shuffle.value
    }

    fun cancionAnterior(){
        // si es la primera cambia a la ultima
        if (ListaCanciones.lista.indexOf(_cancion.value) == 0){
            cambiarCancion(ListaCanciones.lista[ListaCanciones.lista.size - 1])
        } else {
            cambiarCancion(ListaCanciones.lista[ListaCanciones.lista.indexOf(_cancion.value) - 1])
        }
    }

    fun cancionSiguiente(){
        // si es la ultima cambia a la primera
        if (ListaCanciones.lista.indexOf(_cancion.value) == ListaCanciones.lista.size - 1){
            cambiarCancion(ListaCanciones.lista[0])
        } else {
            cambiarCancion(ListaCanciones.lista[ListaCanciones.lista.indexOf(_cancion.value) + 1])
        }
    }
}