package com.uid.smartmobilityapp.ui.flexible_intent

import android.location.Address
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import com.uid.smartmobilityapp.ui.flexible_intent.model.MyFlexibleIntents

object FlexibleIntentViewModel : ViewModel() {
    val flexibleIntents : LiveData<ArrayList<FlexibleIntent>> = MutableLiveData<ArrayList<FlexibleIntent>>().apply {
        value = MyFlexibleIntents.flexibleIntents
    }

    val selectedName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val selectedDay: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val selectedFromHour: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val selectedToHour: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }

}