package com.uid.smartmobilityapp.ui.bookmarks.model

import android.location.Address
import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity
import java.util.*
import kotlin.collections.ArrayList

object MyBookmarks {
    val bookmarks : List<Bookmark> = mutableListOf(
        Bookmark("Home",
            Geocoder(MainActivity.context).getFromLocationName(
                "Str. Donath 15, Cluj-Napoca", 1).get(0)),
        Bookmark("Work",
            Geocoder(MainActivity.context).getFromLocationName(
                "The Office Cluj-Napoca, Bulevardul 21 Decembrie 1989, Cluj-Napoca", 1).get(0)),
        Bookmark("School",
            Geocoder(MainActivity.context).getFromLocationName(
                "Strada George Barițiu 26, Cluj-Napoca", 1).get(0)),
    )
}