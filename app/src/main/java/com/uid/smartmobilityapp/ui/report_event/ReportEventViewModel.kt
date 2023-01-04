package com.uid.smartmobilityapp.ui.report_event

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.services.DeviceLocationProviderService
import java.time.LocalDateTime

object ReportEventViewModel : ViewModel() {

    val selectedStartDate: MutableLiveData<LocalDateTime?> = MutableLiveData<LocalDateTime?>().apply {
        value = null
    }

    val selectedEndDate: MutableLiveData<LocalDateTime?> = MutableLiveData<LocalDateTime?>().apply {
        value = null
    }

    val selectedEventType : MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }

    val selectedLocation: MutableLiveData<AddressWithName?> = MutableLiveData<AddressWithName?>().apply {
        value = null
    }

    val newlySelectedLocation: MutableLiveData<AddressWithName?> = MutableLiveData<AddressWithName?>().apply {
        value = AddressWithName(DeviceLocationProviderService().getCurrentLocation(), DeviceLocationProviderService().getCurrentLocation().getAddressLine(0))
    }



    fun clear() {
        selectedStartDate.value = null;
        selectedEndDate.value = null;
        selectedEventType.value = null;
        selectedLocation.value = null
        newlySelectedLocation.value = selectedLocation.value
    }
}