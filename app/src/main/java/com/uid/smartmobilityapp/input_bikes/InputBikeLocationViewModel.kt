package com.uid.smartmobilityapp.input_bikes

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.input_bikes.model.InputBike
import com.uid.smartmobilityapp.input_bikes.model.MyInputBikes
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.services.DeviceLocationProviderService

class InputBikeLocationViewModel : ViewModel() {

    val input_bikes: LiveData<ArrayList<InputBike>> =
        MutableLiveData<ArrayList<InputBike>>().apply {
            value = MyInputBikes.input_bikes
        }

    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }

    val query: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = null
    }

}