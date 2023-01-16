package com.uid.smartmobilityapp.ui.my_regular_intents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.regular_intent.model.MyRegularIntents
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntent

class MyRegularIntentsViewModel : ViewModel() {

    val intents: LiveData<ArrayList<RegularIntent>> = MutableLiveData<ArrayList<RegularIntent>>().apply {
        value = MyRegularIntents.regularIntents
    }

}