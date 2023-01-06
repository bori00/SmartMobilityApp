package com.uid.smartmobilityapp.ui.my_flexible_intents.model

import com.uid.smartmobilityapp.R

object MyTransportations {
    val vehicles: ArrayList<Transportation> = arrayListOf(
        Transportation(
            R.drawable.bus1,
            "Bus",
            "10 min",
            "13:10-13:20",
            "departs at 13:10 from Observator Nord",
        ),
        Transportation(
            R.drawable.bus2,
            "Bus",
            "10 min",
            "13:20-13:30",
            "departs at 13:20 from Maresal C-tin Prezan",
        )
    )

    val car: ArrayList<Transportation> = arrayListOf(
        Transportation(
            R.drawable.ic_baseline_directions_car_24,
            "Car",
            "9 min",
            "12:20-12:29",
            "depart at 12:20 from Observator",
        )
    )
}