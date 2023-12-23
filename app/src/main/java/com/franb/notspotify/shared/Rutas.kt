package com.franb.notspotify.shared

sealed class Rutas(val ruta: String) {
    //object PantallaCancion: Rutas("cancion/{idCancion}") TODO navegar a cancion
    object PantallaCancion: Rutas("cancion")

}