package com.franb.notspotify

class ListaCanciones(){
    companion object{
        var lista = ArrayList<Cancion>()

        fun cargarCanciones(){
            lista.add(Cancion("Tokyo", R.drawable.smt4,R.raw.smt4_tokyo, null))
            lista.add(Cancion("Battle C2", R.drawable.smt4,R.raw.smt4_c2, null))
            lista.add(Cancion("Reach out to the truth", R.drawable.p4_ost_art,R.raw.p4_battle, null))
            lista.add(Cancion("Signs of love", R.drawable.p4_ost_art,R.raw.p4_signs_of_love, null))
            lista.add(Cancion("Thousand March", R.drawable.pizzatower_art,R.raw.pizzatower_thousand_march, null))
        }
    }
}