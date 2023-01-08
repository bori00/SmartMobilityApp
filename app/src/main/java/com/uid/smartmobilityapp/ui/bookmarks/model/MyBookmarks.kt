package com.uid.smartmobilityapp.ui.bookmarks.model

import android.location.Address
import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.UserActivity
import java.util.*
import kotlin.collections.ArrayList

object MyBookmarks {
    val bookmarks : ArrayList<Bookmark> = arrayListOf(
        Bookmark("Home",
            Geocoder(UserActivity.context).getFromLocationName(
                "Str. Donath 15, Cluj-Napoca", 1).get(0)),
        Bookmark("Work",
            Geocoder(UserActivity.context).getFromLocationName(
                "The Office Cluj-Napoca, Bulevardul 21 Decembrie 1989, Cluj-Napoca", 1).get(0)),
        Bookmark("School",
            Geocoder(UserActivity.context).getFromLocationName(
                "Strada George Barițiu 26, Cluj-Napoca", 1).get(0)),
    )
}