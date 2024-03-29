package com.uid.smartmobilityapp.ui.profile_setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.ui.profile_setup.model.MyUser
import com.uid.smartmobilityapp.ui.profile_setup.model.UserModel

class ProfileSetupViewModel : ViewModel() {
    var user: LiveData<UserModel> =
        MutableLiveData<UserModel>().apply {
            value = MyUser.defaultUser
        }
}