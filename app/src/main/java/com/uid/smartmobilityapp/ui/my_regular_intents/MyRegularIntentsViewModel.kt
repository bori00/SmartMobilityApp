package com.uid.smartmobilityapp.ui.my_regular_intents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyRegularIntentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is 'My regular intents' Fragment"
    }
    val text: LiveData<String> = _text
}