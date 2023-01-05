package com.uid.smartmobilityapp.ui.profile_setup.model

import java.util.*
import kotlin.collections.ArrayList

class UserModel(
    var hasDrivingLicense: Boolean,
    var canRideBike: Boolean, var canRideScooter: Boolean, var maxWalkingDistance: String,
    var hasPublicTransportPass: Boolean, var expiryDate: Date = Date(),
    var vehicleList: ArrayList<String>
) {
}