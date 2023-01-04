package com.uid.smartmobilityapp.ui.rate_ride

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime

object RateRideViewModel : ViewModel() {

    val selectedDate: MutableLiveData<LocalDate?> = MutableLiveData<LocalDate?>().apply {
        value = null
    }
    val selectedRide: MutableLiveData<String?> = MutableLiveData<String?>().apply {
        value = null
    }
}