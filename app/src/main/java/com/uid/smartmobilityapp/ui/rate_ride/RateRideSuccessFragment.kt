package com.uid.smartmobilityapp.ui.rate_ride

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentRateRideBinding
import com.uid.smartmobilityapp.databinding.FragmentRateRideSuccessBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer

class RateRideSuccessFragment : Fragment() {

    private var _binding: FragmentRateRideSuccessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = RateRideViewModel

        _binding = FragmentRateRideSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.backButton.setOnClickListener {
            binding.root.findNavController().navigate(com.uid.smartmobilityapp.R.id.action_nav_rate_ride_success_to_nav_home)
        }

        binding.successMessage.text = "Thank you for sharing your experiences about your ${viewModel.selectedRide.value} ride on ${getDateString(viewModel.selectedDate.value!!)} and allowing us to improve our services!"


        viewModel.selectedDate.value = null
        viewModel.selectedRide.value = null

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDateString(localDate: LocalDate) : String {
        return "${localDate.dayOfMonth}-${localDate.month}-${localDate.year}"
    }
}