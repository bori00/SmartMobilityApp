package com.uid.smartmobilityapp.ui.travel_now

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations

class LocationsViewModel: ViewModel() {
    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }

    val locations : LiveData<ArrayList<Location>> = MutableLiveData<ArrayList<Location>>().apply {
        value = MyLocations.locations
    }

}