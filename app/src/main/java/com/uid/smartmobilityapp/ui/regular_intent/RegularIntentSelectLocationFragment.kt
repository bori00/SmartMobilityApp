package com.uid.smartmobilityapp.ui.regular_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSetupBinding
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel

class RegularIntentSelectLocationFragment : Fragment() {

    private lateinit var viewModel: RegularIntentViewModel
    private lateinit var viewModelForLocations: LocationsViewModel
    private var _binding: FragmentFlexibleIntentSetupBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegularIntentViewModel
        viewModelForLocations = LocationsViewModel

        _binding = FragmentFlexibleIntentSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        //setup()

        return _root
    }

}