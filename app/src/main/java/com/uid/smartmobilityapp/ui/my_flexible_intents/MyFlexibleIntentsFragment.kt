package com.uid.smartmobilityapp.ui.my_flexible_intents

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentMyFlexibleIntentsBinding
import com.uid.smartmobilityapp.ui.flexible_intent.FlexibleIntentViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.MyFlexibleIntentions

class MyFlexibleIntentsFragment : Fragment() {

    private var _binding: FragmentMyFlexibleIntentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val _viewModel =
            ViewModelProvider(this).get(MyFlexibleIntentsViewModel::class.java)

        _binding = FragmentMyFlexibleIntentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val spinner: Spinner = binding.spinner
        val futureIntentsNames= ArrayList<String>()

//        for (loc: FlexibleIntention in MyFlexibleIntentions.flexibleIntents) {
//            futureIntentsNames.add(loc.name)
//        }

        for (loc: FlexibleIntent in FlexibleIntentViewModel.flexibleIntents.value!!) {
            futureIntentsNames.add(loc.name)
        }

//        val arrayAdapter = ArrayAdapter(MainActivity.context, R.layout.simple_spinner_item, futureIntentsNames)
//        spinner.adapter = arrayAdapter
//
//        val text: String = spinner.selectedItem.toString()

        val nextButton: Button = binding.nextButtonId
        nextButton.setOnClickListener {
            binding.root.findNavController().navigate(com.uid.smartmobilityapp.R.id.action_flexible_intents_to_optimal_route)
        }

        val dropdown = binding.selectOptimizationAutoComplete
        val adapter =
            activity?.let {
                ArrayAdapter(
                    it,
                    R.layout.simple_spinner_dropdown_item,
                    futureIntentsNames
                ) }
        dropdown.setAdapter(adapter)

        nextButton.isEnabled = false

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                nextButton.isEnabled = selectedWord == "Get groceries"
            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        val futureIntentsNames= ArrayList<String>()

        for (loc: FlexibleIntent in FlexibleIntentViewModel.flexibleIntents.value!!) {
            futureIntentsNames.add(loc.name)
        }

        val dropdown = binding.selectOptimizationAutoComplete
        val adapter =
            activity?.let {
                ArrayAdapter(
                    it,
                    R.layout.simple_spinner_dropdown_item,
                    futureIntentsNames
                ) }
        dropdown.setAdapter(adapter)
    }
}