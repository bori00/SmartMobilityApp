package com.uid.smartmobilityapp.ui.profile_setup.model

import java.time.LocalDateTime
import java.time.Month
import kotlin.collections.ArrayList

class UserModel(
    var hasDrivingLicense: Boolean,
    var canRideBike: Boolean, var canRideScooter: Boolean, var maxWalkingDistance: String,
    var hasPublicTransportPass: Boolean, var expiryDate: LocalDateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 10, 30, 0),
    var vehicleList: ArrayList<String>
) {
}