package com.franb.notspotify.shared

data class Cancion(
    val nombre: String,
    val imagen: Int,
    val archivo: Int,
    val album: Album? = null,
)
