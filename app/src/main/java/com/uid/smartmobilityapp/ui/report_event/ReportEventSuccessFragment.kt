package com.uid.smartmobilityapp.ui.report_event

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import com.uid.smartmobilityapp.databinding.FragmentReportEventSuccessBinding
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer


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
        viewModel =
            ViewModelProvider(this)[ReportEventViewModel::class.java]

        _binding = FragmentReportEventSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupBackButton()

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