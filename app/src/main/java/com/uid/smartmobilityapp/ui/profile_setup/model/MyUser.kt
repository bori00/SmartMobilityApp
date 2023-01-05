package com.uid.smartmobilityapp.ui.profile_setup.model

import java.util.*

object MyUser {

    var defaultUser: UserModel = UserModel(
        hasDrivingLicense = false,
        hasPublicTransportPass = false,
        expiryDate = Date(),
        canRideBike = false,
        canRideScooter = false,
        maxWalkingDistance = "0",
        vehicleList = arrayListOf("Car", "Bike")
    )
}