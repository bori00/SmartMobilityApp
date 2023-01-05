package com.uid.smartmobilityapp.ui.profile_setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentProfileSetupBinding

class ProfileSetupFragment : Fragment() {
    private var _binding: FragmentProfileSetupBinding? = null
    lateinit private var _viewModel: ProfileSetupViewModel
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(ProfileSetupViewModel::class.java)

        _binding = FragmentProfileSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        setupSaveButton()
        setupCancelButton()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun setupSaveButton() {
        val saveButton: Button = binding.saveButtonID
        saveButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_view_vehicles_to_locations)
        }
    }

    private fun setupCancelButton() {
        val cancelButton: Button = binding.cancelButtonID
        cancelButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_view_vehicles_to_locations)
        }
    }
}