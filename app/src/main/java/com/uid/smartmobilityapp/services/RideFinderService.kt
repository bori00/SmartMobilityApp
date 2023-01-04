package com.uid.smartmobilityapp.services

import java.time.LocalDate

class RideFinderService {
    fun findRidesOnDate(date : LocalDate?) : List<String> {
        if (date == null) {
            return mutableListOf()
        }
        if (date.dayOfMonth % 3 == 0) {
            return mutableListOf("Baritiu 26 -> Muncii 100",
                "Muncii 100 -> Observatorului 90",
                "Home -> Baritiu 26"
            )
        } else if (date.dayOfMonth % 3 == 1) {
            return mutableListOf(
                "Home -> Baritiu 26"
            )
        } else {
            return mutableListOf("Home -> Lidl Baciu",
                "Lidl Baciu -> Home",
                "Baritiu 26 -> Muncii 100",
                "Muncii 100 -> Observatorului 90",
                "Home -> Baritiu 26"
            )
        }
    }
}