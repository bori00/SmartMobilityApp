package com.uid.smartmobilityapp.ui.flexible_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectDestinationBinding
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectOptimizationBinding
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent

class FlexibleIntentSelectDestinationFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentViewModel
    private var _binding: FragmentFlexibleIntentSelectDestinationBinding? = null
    private lateinit var _root : View;
    private lateinit var nextButton: Button

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel

        _binding = FragmentFlexibleIntentSelectDestinationBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    fun setup() {
        nextButton = _root.findViewById<Button>(R.id.saveFlexibleButton)
        nextButton.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_nav_flexible_intent_select_optimization_to_nav_flexible_intent_success)
        }
        nextButton.isEnabled = false

    }

    private fun updateButtonState() {

    }

}