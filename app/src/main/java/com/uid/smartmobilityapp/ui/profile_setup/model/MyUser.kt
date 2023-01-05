package com.uid.smartmobilityapp.ui.profile_setup.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.util.*

object MyUser {

    var defaultUser: UserModel = UserModel(
        hasDrivingLicense = false,
        hasPublicTransportPass = false,
        expiryDate = LocalDateTime.of(2024, Month.JANUARY, 1, 10, 30, 0),
        canRideBike = false,
        canRideScooter = false,
        maxWalkingDistance = "0",
        vehicleList = arrayListOf("Car", "Bike")
    )
}