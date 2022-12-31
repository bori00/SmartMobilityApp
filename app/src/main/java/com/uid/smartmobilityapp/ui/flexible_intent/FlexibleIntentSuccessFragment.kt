package com.uid.smartmobilityapp.ui.flexible_intent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSuccessBinding

class FlexibleIntentSuccessFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentViewModel
    private var _binding: FragmentFlexibleIntentSuccessBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel

        _binding = FragmentFlexibleIntentSuccessBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    fun setup() {
        val nextButtonClick = _root.findViewById<Button>(R.id.backButton)
        nextButtonClick.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_nav_flexible_intent_success_to_nav_home)
        }

        val successMessage = _root.findViewById<TextView>(R.id.successMessage)
        successMessage.text =
            buildString {
                append("Your trip for \"")
                append(FlexibleIntentViewModel.selectedName.value)
                append("\" flexible intent has been scheduled")
            }
    }

}