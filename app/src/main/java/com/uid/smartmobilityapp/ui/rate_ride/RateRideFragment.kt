package com.uid.smartmobilityapp.ui.rate_ride

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.databinding.FragmentRateRideBinding

class RateRideFragment : Fragment() {

    private var _binding: FragmentRateRideBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(RateRideViewModel::class.java)

        _binding = FragmentRateRideBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.rateRideTitleTextView
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