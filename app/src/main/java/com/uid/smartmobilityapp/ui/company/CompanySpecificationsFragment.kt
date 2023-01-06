package com.uid.smartmobilityapp.ui.company

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uid.smartmobilityapp.R

class CompanySpecificationsFragment : Fragment() {

    companion object {
        fun newInstance() = CompanySpecificationsFragment()
    }

    private lateinit var viewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_specifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = CompanyViewModel
        // TODO: Use the ViewModel
    }

}