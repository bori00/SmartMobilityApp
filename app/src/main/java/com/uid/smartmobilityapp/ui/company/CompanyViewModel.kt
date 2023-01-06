package com.uid.smartmobilityapp.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uid.smartmobilityapp.models.CompanyDetails

object CompanyViewModel : ViewModel() {
    val companies : LiveData<ArrayList<CompanyDetails>> = MutableLiveData<ArrayList<CompanyDetails>>().apply {
        value = MyCompanies.companies
    }

    val loggedInUsername: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val loggedInEmail: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
}