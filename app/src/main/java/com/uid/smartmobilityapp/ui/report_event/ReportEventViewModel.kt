package com.uid.smartmobilityapp.ui.report_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportEventViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the 'Report event' Fragment"
    }
    val text: LiveData<String> = _text
}