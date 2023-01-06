package com.uid.smartmobilityapp.ui.home

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentHomeBinding
import com.uid.smartmobilityapp.ui.regular_intent.RegularIntentViewModel
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntent
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModelForLocations: LocationsViewModel
    private lateinit var viewModelRegularIntent: RegularIntentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelForLocations = LocationsViewModel
        viewModelForLocations.selectedIntent.value = ""
        viewModelRegularIntent = RegularIntentViewModel
        clearRegularIntentViewModel()
        initializeLocationsWithCurrentLocation()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val travelNowButton: Button = binding.travelNowButtonId
        travelNowButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_nav_home_to_travel_now)
        }

        val scheduleRegularIntentButton: Button = binding.scheduleRegularIntentButton
        scheduleRegularIntentButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_nav_home_to_regular_intent_setup)
        }

        val scheduleFlexibleIntentButtonClick: Button = binding.scheduleFlexibleIntentButton
        scheduleFlexibleIntentButtonClick.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_nav_home_to_nav_flexible_intent_setup)
        }

        return root
    }

    private fun initializeLocationsWithCurrentLocation() {
        MyLocations.locations = arrayListOf(
            Location("CurrentLocation",
                "1",
                Geocoder(MainActivity.context).getFromLocationName(
                    "Str. Donath 15, Cluj-Napoca", 1).get(0))
        )
        viewModelForLocations.locations.value = MyLocations.locations
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearRegularIntentViewModel() {
        viewModelRegularIntent.selectedName.value = ""
        viewModelRegularIntent.selectedDay.value = ""
        viewModelRegularIntent.selectedArrivalTime.value = "00:00"
        viewModelRegularIntent.startingPointNewAddress.value = null
        viewModelRegularIntent.destinationNewAddress.value = null
        viewModelRegularIntent.previousLocation.value = null
        viewModelRegularIntent.startingPoint.value =
            RegularIntentLocation(null,
                "1",
                null,
                "Specify starting point"
            )
        viewModelRegularIntent.destination.value =
            RegularIntentLocation(null,
            "2",
            null,
            "Specify destination"
        )
    }
}