package com.uid.smartmobilityapp.ui.my_regular_intents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.my_regular_intents.model.MyRegularIntents

class MyRegularIntentsViewModel : ViewModel() {

    val intents: LiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>().apply {
        value = MyRegularIntents.regularIntents
    }

}