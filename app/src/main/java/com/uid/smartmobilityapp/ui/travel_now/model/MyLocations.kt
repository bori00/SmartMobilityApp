package com.uid.smartmobilityapp.ui.travel_now.model

import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity

object MyLocations {
    val locations : ArrayList<Location> = arrayListOf(
        Location("CurrentLocation",
            "1",
            Geocoder(MainActivity.context).getFromLocationName(
            "Str. Donath 15, Cluj-Napoca", 1).get(0))
    )
}