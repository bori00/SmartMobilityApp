package com.uid.smartmobilityapp.ui.profile_setup.model

import java.util.*

object MyUser {

    var user: UserModel = UserModel(
        hasDrivingLicense = true,
        hasPublicTransportPass = true,
        expiryDate = Date(),
        canRideBike = true,
        canRideScooter = false,
        maxWalkingDistance = "5000",
        vehicleList = arrayListOf("Car", "Bike")
    )
}