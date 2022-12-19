package com.uid.smartmobilityapp.ui.flexible_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectTransportBinding

class FlexibleIntentSelectTransportFragment : Fragment() {

    private lateinit var viewModel: FlexibleIntentSelectTransportViewModel
    private var _binding: FragmentFlexibleIntentSelectTransportBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FlexibleIntentSelectTransportViewModel::class.java)

        _binding = FragmentFlexibleIntentSelectTransportBinding.inflate(inflater, container, false)
        _root = binding.root

        val nextButtonClick = _root.findViewById<Button>(R.id.nextButton)
        nextButtonClick.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_nav_flexible_intent_select_transport_to_nav_flexible_intent_select_optimization)
        }

        return _root
    }

}