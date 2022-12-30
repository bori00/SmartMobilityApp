package com.uid.smartmobilityapp.services

import android.location.Address
import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity

class DeviceLocationProviderService {
    fun getCurrentLocation() : Address {
        return Geocoder(MainActivity.context).getFromLocationName(
            "Technical University of Cluj-Napoca - Faculty of Automotive, Mechatronics and Mechanical Engineering", 1).get(0)
    }
}