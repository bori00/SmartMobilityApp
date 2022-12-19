package com.uid.smartmobilityapp.ui.flexible_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectOptimizationBinding

class FlexibleIntentSelectOptimizationFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentSelectOptimizationViewModel
    private var _binding: FragmentFlexibleIntentSelectOptimizationBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FlexibleIntentSelectOptimizationViewModel::class.java)

        _binding = FragmentFlexibleIntentSelectOptimizationBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    fun setup() {
        val dropdown = _root.findViewById<AutoCompleteTextView>(R.id.selectOptimizationAutoComplete)
        val items = arrayOf("As fast as possible", "Minimal carbon footprint", "Cheapest option")
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        dropdown.setAdapter(adapter)

        val nextButtonClick = _root.findViewById<Button>(R.id.saveFlexibleButton)
        nextButtonClick.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_nav_flexible_intent_select_optimization_to_nav_flexible_intent_success)
        }
    }
}