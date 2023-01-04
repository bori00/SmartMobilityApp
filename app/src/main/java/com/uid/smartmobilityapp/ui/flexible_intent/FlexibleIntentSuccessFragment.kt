package com.uid.smartmobilityapp.ui.flexible_intent

import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectOptimizationBinding
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSuccessBinding
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations

class FlexibleIntentSuccessFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentViewModel
    private lateinit var viewModelForLocations: LocationsViewModel
    private var _binding: FragmentFlexibleIntentSuccessBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel
        viewModelForLocations = LocationsViewModel

        viewModelForLocations.selectedIntent.value = ""
        MyLocations.locations = arrayListOf(
            Location("CurrentLocation",
                "1",
                Geocoder(MainActivity.context).getFromLocationName(
                    "Str. Donath 15, Cluj-Napoca", 1).get(0))
        )

        _binding = FragmentFlexibleIntentSuccessBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    fun setup() {
        val nextButtonClick = _root.findViewById<Button>(R.id.backButton)
        nextButtonClick.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_nav_flexible_intent_success_to_nav_home)
        }

        val successMessage = _root.findViewById<TextView>(R.id.successMessage)
        successMessage.text =
            buildString {
                append("Your trip for \"")
                append(viewModel.selectedName.value)
                append("\" flexible intent has been scheduled")
            }
    }

}