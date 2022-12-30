package com.uid.smartmobilityapp.ui.home

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _buttonTravelNow = MutableLiveData<Button>().apply {

    }
    val text: LiveData<String> = _text
    val button:LiveData<Button> = _buttonTravelNow
}