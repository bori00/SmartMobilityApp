package com.uid.smartmobilityapp.ui.bookmarks

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks

class AddBookmarkViewModel : ViewModel() {
    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }
    val selectedName: LiveData<String> = MutableLiveData<String>().apply {
        value = ""
    }
}