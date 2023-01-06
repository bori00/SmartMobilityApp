package com.uid.smartmobilityapp.input_bikes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.input_bikes.model.InputBike
import com.uid.smartmobilityapp.input_bikes.model.MyInputBikes

class InputBikeLocationViewModel : ViewModel() {

    val input_bikes: LiveData<ArrayList<InputBike>> =
        MutableLiveData<ArrayList<InputBike>>().apply {
            value = MyInputBikes.input_bikes
        }

}