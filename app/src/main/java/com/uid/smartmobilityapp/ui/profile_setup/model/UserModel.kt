package com.uid.smartmobilityapp.ui.profile_setup.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class UserModel(
    var hasDrivingLicense: Boolean,
    var canRideBike: Boolean, var canRideScooter: Boolean, var maxWalkingDistance: String,
    var hasPublicTransportPass: Boolean, var expiryDate: LocalDateTime = LocalDateTime.now(),
    var vehicleList: ArrayList<String>
) {
}