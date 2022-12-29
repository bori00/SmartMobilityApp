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
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectOptimizationBinding
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent

class FlexibleIntentSelectOptimizationFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentViewModel
    private var _binding: FragmentFlexibleIntentSelectOptimizationBinding? = null
    private lateinit var _root : View;
    private lateinit var nextButton: Button
    private var selectedOptimization : String = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel

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

        nextButton = _root.findViewById<Button>(R.id.saveFlexibleButton)
        nextButton.setOnClickListener {view ->
            viewModel.flexibleIntents.value?.add(
                FlexibleIntent(
                    viewModel.selectedName.value!!,
                    null,
                    null,
                    null,
                )
            )
            view.findNavController().navigate(R.id.action_nav_flexible_intent_select_optimization_to_nav_flexible_intent_success)
        }
        nextButton.isEnabled = false

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                selectedOptimization = selectedWord
                updateButtonState()
            }

        val oneLastThingLabel = _root.findViewById<TextView>(R.id.oneLastThingLabel)
        oneLastThingLabel.text =
            buildString {
                append("One last thing for \"")
                append(viewModel.selectedName.value)
                append("\"")
            }
    }

    private fun updateButtonState() {
        nextButton.isEnabled = selectedOptimization != ""
    }
}