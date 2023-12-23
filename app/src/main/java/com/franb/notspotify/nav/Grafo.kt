package com.franb.notspotify.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.franb.notspotify.shared.Rutas
import com.franb.notspotify.vistas.ComponenteCancion

@Composable
fun GrafoNavegacion(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PantallaCancion.ruta) {

        composable(Rutas.PantallaCancion.ruta){
            ComponenteCancion(navController = navController)
        }

        /*
        composable(Rutas.PantallaDos.ruta + "/{cosa}"){
                llamada ->
            var nombreRecibido = llamada.arguments?.getInt("cosa")
            if (nombreRecibido != null) {
                PantallaSaludo(nombreRecibido)
            }
        }
         */
    }
}