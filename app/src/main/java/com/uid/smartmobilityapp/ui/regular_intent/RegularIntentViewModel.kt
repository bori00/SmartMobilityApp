package com.uid.smartmobilityapp.ui.regular_intent

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.ui.regular_intent.model.MyRegularIntents
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntent
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation

object RegularIntentViewModel : ViewModel() {

    val regularIntents : LiveData<ArrayList<RegularIntent>> = MutableLiveData<ArrayList<RegularIntent>>().apply {
        value = MyRegularIntents.regularIntents
    }


    val startingPoint: MutableLiveData<RegularIntentLocation> = MutableLiveData<RegularIntentLocation>().apply {
        value = RegularIntentLocation(null,
            "1",
            null,
            "Specify starting point"
        )
    }

    val destination: MutableLiveData<RegularIntentLocation> = MutableLiveData<RegularIntentLocation>().apply {
        value = RegularIntentLocation(null,
            "2",
            null,
            "Specify destination"
        )
    }

    val startingPointNewAddress: MutableLiveData<AddressWithName?> = MutableLiveData<AddressWithName?>().apply {
        value = null
    }

    val destinationNewAddress: MutableLiveData<AddressWithName?> = MutableLiveData<AddressWithName?>().apply {
        value = null
    }

    val previousLocation: MutableLiveData<AddressWithName?> = MutableLiveData<AddressWithName?>().apply {
        value = null
    }

    val selectedName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val selectedDay: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val selectedArrivalTime: MutableLiveData<String> = MutableLiveData<String>().apply { value = "__:__" }

}