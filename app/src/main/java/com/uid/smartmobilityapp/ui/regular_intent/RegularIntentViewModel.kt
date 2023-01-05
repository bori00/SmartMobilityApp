package com.uid.smartmobilityapp.ui.regular_intent

import android.location.Address
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object RegularIntentViewModel : ViewModel() {


    val startingPoint: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }

    val destination: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }
}