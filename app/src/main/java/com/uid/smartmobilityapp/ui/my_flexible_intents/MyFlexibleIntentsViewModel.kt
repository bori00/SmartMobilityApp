package com.uid.smartmobilityapp.ui.my_flexible_intents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyFlexibleIntentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is 'My flexible intents' Fragment"
    }
    val text: LiveData<String> = _text
}