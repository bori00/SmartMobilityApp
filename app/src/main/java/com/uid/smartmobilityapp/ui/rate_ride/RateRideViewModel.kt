package com.uid.smartmobilityapp.ui.rate_ride

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RateRideViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the 'Rate a ride' Fragment"
    }
    val text: LiveData<String> = _text
}