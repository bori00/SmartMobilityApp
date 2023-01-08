package com.uid.smartmobilityapp.input_bikes.model

import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity

object MyInputBikes {
    val input_bikes: ArrayList<InputBike> = arrayListOf(
        InputBike(
            "Str. Observatorului, nr. 35",
            "20",
            //TODO from main activity to company activity
            Geocoder(MainActivity.context).getFromLocationName(
                "Str. Donath 15, Cluj-Napoca", 1
            ).get(0)
        )
    )
}