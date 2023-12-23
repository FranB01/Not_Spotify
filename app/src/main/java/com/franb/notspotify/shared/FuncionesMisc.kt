package com.franb.notspotify.shared

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.AnyRes

fun MilisAString(input : Long) : String{
    var segundosTotal = input / 1000
    val minutos = segundosTotal / 60
    val segundos = segundosTotal % 60
    val minutosString = /*if (minutos < 10) "0$minutos" else*/ minutos.toString()
    val segundosString = if (segundos < 10) "0$segundos" else segundos.toString()
    return "$minutosString:$segundosString"
}

// Funcion auxiliar que devuelve la ruta de un fichero a partir de su ID
@Throws(Resources.NotFoundException::class)
fun obtenerRuta(context: Context, @AnyRes resId: Int): Uri {
    val res: Resources = context.resources
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
    )
}