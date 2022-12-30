package com.uid.smartmobilityapp.ui.report_event

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class ReportEventViewModel : ViewModel() {

    val selectedStartDate: MutableLiveData<LocalDateTime> = MutableLiveData<LocalDateTime>().apply {
        LocalDateTime.now()
    }

    val selectedEndDate: MutableLiveData<LocalDateTime> = MutableLiveData<LocalDateTime>().apply {
        LocalDateTime.now().plusDays(1)
    }

    val selectedEventType : MutableLiveData<String?> = MutableLiveData<String?>().apply { null }
}