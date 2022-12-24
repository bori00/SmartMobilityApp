package com.uid.smartmobilityapp.ui.flexible_intent

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FlexibleIntentSelectDestinationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        val oneLastThingLabel = _root.findViewById<TextView>(R.id.oneLastThingLabel)
        oneLastThingLabel.text =
            buildString {
                append("One last thing for \"")
                append(viewModel.selectedName.value)
                append("\"")
            }
    }

    private fun updateButtonState() {
        //nextButton.isEnabled = selectedOptimization != ""
    }
}