package com.uid.smartmobilityapp.ui.bookmarks

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks

class BookmarksViewModel : ViewModel() {

    val bookmarks : LiveData<ArrayList<Bookmark>> = MutableLiveData<ArrayList<Bookmark>>().apply {
        value = MyBookmarks.bookmarks
    }
    val selectedAddress: MutableLiveData<Address> = MutableLiveData<Address>().apply {
        value = null
    }
}