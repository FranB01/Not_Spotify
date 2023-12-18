package com.franb.notspotify.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CancionViewModel: ViewModel(){
    // Esta variable privada no es accesible desde fuera, y representa
    // el flow de nuestra variable de estado.
    private val _redondeo = MutableStateFlow(false)

    // Esta variable publica recoge y actualiza el valor del estado mutable, y es el que usará
    // la interfaz.
    val redondeo = _redondeo.asStateFlow()

    fun actualizarSwitch(nuevoValor : Boolean){
        _redondeo.value = nuevoValor
    }

    private val _playing = MutableStateFlow(false)
    val playing = _playing.asStateFlow()


}