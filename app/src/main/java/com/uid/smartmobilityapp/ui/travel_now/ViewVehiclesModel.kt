package com.uid.smartmobilityapp.ui.travel_now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.travel_now.model.MyVehicles
import com.uid.smartmobilityapp.ui.travel_now.model.VehicleListItem

class ViewVehiclesModel: ViewModel() {

    val vehicles : LiveData<ArrayList<VehicleListItem>> = MutableLiveData<ArrayList<VehicleListItem>>().apply {
        value = MyVehicles.vehicles
    }
}