package com.uid.smartmobilityapp.ui.my_flexible_intents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.FlexibleIntention
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.MyFlexibleIntentions
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.MyTransportations
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.Transportation
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations
import com.uid.smartmobilityapp.ui.travel_now.model.MyVehicles.vehicles

class MyFlexibleIntentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is 'My flexible intents' Fragment"
    }
    val text: LiveData<String> = _text


    val flexibleIntents : LiveData<ArrayList<FlexibleIntention>> = MutableLiveData<ArrayList<FlexibleIntention>>().apply {
        value = MyFlexibleIntentions.flexibleIntents
    }

    val vehicles: MutableLiveData<ArrayList<Transportation>> = MutableLiveData<ArrayList<Transportation>>().apply {
        value = MyTransportations.vehicles
    }

    val car: MutableLiveData<ArrayList<Transportation>> = MutableLiveData<ArrayList<Transportation>>().apply {
        value = MyTransportations.car
    }

}