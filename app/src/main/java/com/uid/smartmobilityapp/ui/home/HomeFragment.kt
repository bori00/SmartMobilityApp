package com.uid.smartmobilityapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentHomeBinding
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModelForLocations: LocationsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelForLocations = LocationsViewModel
        viewModelForLocations.selectedIntent.value = ""

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val travelNowButton: Button = binding.travelNowButtonId
        travelNowButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_nav_home_to_travel_now)
        }

        val scheduleFlexibleIntentButtonClick: Button = binding.scheduleFlexibleIntentButton
        scheduleFlexibleIntentButtonClick.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_nav_home_to_nav_flexible_intent_setup)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}