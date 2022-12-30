package com.uid.smartmobilityapp.services

import android.location.Address
import android.location.Geocoder
import com.uid.smartmobilityapp.MainActivity

class DeviceLocationProviderService {
    fun getCurrentLocation() : Address {
        return Geocoder(MainActivity.context).getFromLocationName(
            "UTCN - Facultatea de Autovehicule Rutiere Bd Muncii", 1).get(0)
    }
}