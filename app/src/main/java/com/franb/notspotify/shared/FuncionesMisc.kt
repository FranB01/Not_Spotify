package com.franb.notspotify.shared

fun MilisAString(input : Long) : String{
    var segundosTotal = input / 1000
    val minutos = segundosTotal / 60
    val segundos = segundosTotal % 60
    val minutosString = /*if (minutos < 10) "0$minutos" else*/ minutos.toString()
    val segundosString = if (segundos < 10) "0$segundos" else segundos.toString()
    return "$minutosString:$segundosString"
}