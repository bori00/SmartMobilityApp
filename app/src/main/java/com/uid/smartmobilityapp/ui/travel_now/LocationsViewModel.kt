package com.uid.smartmobilityapp.ui.travel_now

import android.location.Address
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationsViewModel: ViewModel() {
    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }
}