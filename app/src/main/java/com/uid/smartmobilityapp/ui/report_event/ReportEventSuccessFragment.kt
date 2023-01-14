package com.uid.smartmobilityapp.ui.report_event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentReportEventSuccessBinding


class ReportEventSuccessFragment : Fragment() {

    private var _binding: FragmentReportEventSuccessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : ReportEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ReportEventViewModel

        _binding = FragmentReportEventSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.successMessage.text = "Thank you for reporting this ${viewModel.selectedEventType.value} event happening on ${viewModel.selectedLocation.value?.name} and thus saving time for your peers in the traffic! You're a hero!"

        setupBackButton()
        viewModel.clear() // clear viewmodel after saving data in database

        return root
    }

    private fun setupBackButton() {
        val backButton = binding.backButton

        backButton.setOnClickListener {onBackClick()}
    }

    private fun onBackClick() {
        binding.root.findNavController().navigate(R.id.action_nav_report_event_success_to_nav_home)
    }
}