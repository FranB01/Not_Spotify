package com.franb.notspotify.shared

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.franb.notspotify.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CancionViewModel : ViewModel() {
    private val _exoPlayer: MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val exoPlayer = _exoPlayer.asStateFlow()

    private val _playing = MutableStateFlow(false)
    val playing = _playing.asStateFlow()

    private val _cancion =
        //MutableStateFlow(Cancion("Título de la cancion", R.drawable.smt4, R.raw.smt4_tokyo, null))
        MutableStateFlow(ListaCanciones.lista[0])
    val cancion = _cancion.asStateFlow()

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    private val _tiempoActual = MutableStateFlow(0L)
    val tiempoActual = _tiempoActual.asStateFlow()

    private val _duracion = MutableStateFlow(0L)
    val duracion = _duracion.asStateFlow()

    private val _shuffle = MutableStateFlow(false)
    val shuffle = _shuffle.asStateFlow()

    fun CrearExoPlayer(context: Context) {
        _exoPlayer.value = ExoPlayer.Builder(context).build()
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }

    fun SonarMusica(context: Context){
        var mediaItemCancion = MediaItem.fromUri(obtenerRuta(context,_cancion.value.archivo))
        _exoPlayer.value!!.setMediaItem(mediaItemCancion)
        _exoPlayer.value!!.playWhenReady = true
        _exoPlayer.value!!.addListener(object : Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {
                        // El Player está preparado para empezar la reproducción.
                        // Si playWhenReady es true, empezará a sonar la música.

                        _duracion.value = _exoPlayer.value!!.duration

                        viewModelScope.launch {
                            while(isActive){
                                _tiempoActual.value = _exoPlayer.value!!.currentPosition
                                delay(1000)
                            }

                        }
                    }
                    Player.STATE_BUFFERING -> {
                        // El Player está cargando el archivo, preparando la reproducción.
                        // No está listo, pero está en ello.
                    }
                    Player.STATE_ENDED -> {
                        // El Player ha terminado de reproducir el archivo.
                        CambiarCancion(context)

                    }
                    Player.STATE_IDLE -> {
                        // El player se ha creado, pero no se ha lanzado la operación prepared.
                    }
                }

            }
        }
        )
    }

    // Este método se llama cuando el VM se destruya.
    override fun onCleared() {
        _exoPlayer.value!!.release()
        super.onCleared()
    }

    fun CambiarPlaying(nuevoValor: Boolean) {
        _playing.value = nuevoValor
        if(nuevoValor){
            // play
            _exoPlayer.value!!.play()
        } else {
            // pausar
            _exoPlayer.value!!.pause()
        }
    }

    fun CambiarPlaying() {
        // toggle
        CambiarPlaying(!_playing.value)
    }

    fun CambiarCancion(context: Context) {
        if (_shuffle.value){
            CancionRandom()
        } else if (!_looping.value) { // si esta looping no cambia _cancion pero se recarga el exoplayer
            CancionSiguiente()
        }
        CargarCancion(context)
    }

    fun CargarCancion(context: Context){
        _exoPlayer.value!!.stop()
        _exoPlayer.value!!.clearMediaItems()
        _exoPlayer.value!!.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _cancion.value.archivo)))
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }

    fun CambiarLooping() {
        _looping.value = !_looping.value
    }

    fun CambiarShuffle() {
        _shuffle.value = !_shuffle.value
    }

    fun CancionAnterior() {
        // si es la primera cambia a la ultima
        if (ListaCanciones.lista.indexOf(_cancion.value) == 0) {
            CambiarValorCancion(ListaCanciones.lista[ListaCanciones.lista.size - 1])
        } else {
            CambiarValorCancion(ListaCanciones.lista[ListaCanciones.lista.indexOf(_cancion.value) - 1])
        }
    }

    fun CancionSiguiente() {
        // si es la ultima cambia a la primera
        if (ListaCanciones.lista.indexOf(_cancion.value) == ListaCanciones.lista.size - 1) {
            CambiarValorCancion(ListaCanciones.lista[0])
        } else {
            CambiarValorCancion(ListaCanciones.lista[ListaCanciones.lista.indexOf(_cancion.value) + 1])
        }
    }

    fun CancionRandom(){
        CambiarValorCancion(ListaCanciones.lista.random())
    }

    fun CambiarValorCancion(cancionNueva : Cancion){
        _cancion.value = cancionNueva
    }
}

