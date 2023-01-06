package com.uid.smartmobilityapp.ui.travel_now.model

import com.uid.smartmobilityapp.R

object MyVehicles {

    var vehicles : ArrayList<VehicleListItem> = arrayListOf(
        VehicleListItem(
            "Bus",
            "",
            R.drawable.bus,
            "9:56",
            false,
            false,
            false
        ),
        VehicleListItem(
            "Bike",
            "",
            R.drawable.bike,
            "9:52",
            false,
            true,
            false
        ),
        VehicleListItem(
            "Walk",
            "",
            R.drawable.walk,
            "9:50",
            true,
            true,
            false
        ),
    )
}