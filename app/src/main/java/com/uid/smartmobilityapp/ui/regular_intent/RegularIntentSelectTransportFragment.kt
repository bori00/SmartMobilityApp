package com.uid.smartmobilityapp.ui.regular_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectTransportBinding
import com.uid.smartmobilityapp.ui.flexible_intent.FlexibleIntentViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntent

class RegularIntentSelectTransportFragment : Fragment() {


    private lateinit var viewModel: RegularIntentViewModel
    private var _binding: FragmentFlexibleIntentSelectTransportBinding? = null
    private lateinit var carCheckbox: CheckBox
    private lateinit var bikeCheckbox: CheckBox
    private lateinit var busCheckbox: CheckBox
    private lateinit var walkCheckbox: CheckBox
    private lateinit var nextButton: Button
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegularIntentViewModel

        _binding = FragmentFlexibleIntentSelectTransportBinding.inflate(inflater, container, false)
        _root = binding.root

        carCheckbox = _root.findViewById<CheckBox>(R.id.carCheckBox)
        bikeCheckbox = _root.findViewById<CheckBox>(R.id.bikeCheckBox)
        busCheckbox = _root.findViewById<CheckBox>(R.id.busCheckBox)
        walkCheckbox = _root.findViewById<CheckBox>(R.id.walkCheckBox)

        setCheckboxListener(carCheckbox)
        setCheckboxListener(bikeCheckbox)
        setCheckboxListener(busCheckbox)
        setCheckboxListener(walkCheckbox)

        nextButton = _root.findViewById<Button>(R.id.nextButton)
        nextButton.isEnabled = false
        nextButton.text = "Save"
        nextButton.setOnClickListener { view ->
            viewModel.regularIntents.value?.add(
                RegularIntent(
                    viewModel.selectedName.value!!,
                    null,
                    null,
                    null,
                    null,
                )
            )
            view.findNavController().navigate(R.id.action_nav_regular_intent_select_transport_nav_regular_intent_success)
        }

        val selectTransportLabel = _root.findViewById<TextView>(R.id.selectTransportLabel)
        selectTransportLabel.text =
            buildString {
                append("Select means of transport for \"")
                append(viewModel.selectedName.value)
                append("\"")
            }

        return _root
    }

    private fun setCheckboxListener(checkBox: CheckBox) {
        checkBox.setOnClickListener {
            updateButtonState()
        }
    }

    private fun updateButtonState() {
        nextButton.isEnabled = carCheckbox.isChecked
                || bikeCheckbox.isChecked
                || busCheckbox.isChecked
                || walkCheckbox.isChecked
    }

}