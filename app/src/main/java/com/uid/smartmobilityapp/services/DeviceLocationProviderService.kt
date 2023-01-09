package com.uid.smartmobilityapp.services

import android.location.Address
import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.UserActivity

class DeviceLocationProviderService {
    fun getCurrentLocation() : Address {
        return Geocoder(UserActivity.context).getFromLocationName(
            "UTCN - Facultatea de Autovehicule Rutiere Bd Muncii", 1).get(0)
    }
}