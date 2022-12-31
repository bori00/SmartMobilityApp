package com.uid.smartmobilityapp.ui.my_flexible_intents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.databinding.FragmentOptimalRouteBinding
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import com.uid.smartmobilityapp.ui.report_event.ReportEventViewModel

class GetOptimalRouteFragment: Fragment() {
    private var _binding: FragmentOptimalRouteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(MyFlexibleIntentsViewModel::class.java)

        _binding = FragmentOptimalRouteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
}