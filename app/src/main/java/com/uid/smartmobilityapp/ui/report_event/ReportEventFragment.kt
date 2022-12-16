package com.uid.smartmobilityapp.ui.report_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding

class ReportEventFragment : Fragment() {

    private var _binding: FragmentReportEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(ReportEventViewModel::class.java)

        _binding = FragmentReportEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.reportEventTitleTextView
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}