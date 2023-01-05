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
import com.uid.smartmobilityapp.databinding.FragmentViewProfileBinding

class ProfileViewFragment : Fragment() {
    private var _binding: FragmentViewProfileBinding? = null
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

        _binding = FragmentViewProfileBinding.inflate(inflater, container, false)
        _root = binding.root

        setupEditButton()
        setUpUserData()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupEditButton() {
        val editButton: Button = binding.editButtonID
        editButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_view_profile_to_edit_profile)
        }
    }

    private fun setUpUserData() {
        val driveLic = binding.hasDrivingLicenseTextViewID
        val canRideBike = binding.rideABikeTextViewID
        val canDriveScooter = binding.rideScooterTextViewID
        val maxDist = binding.maxWalkingDistanceTextViewID
        val publicTrPass = binding.hasPublicTransTextViewID
        val vehicle = binding.hasVehicleTextViewID
        val expiryDate = binding.expiryDateTextViewID

        maxDist.text = _viewModel.user.value?.maxWalkingDistance
        vehicle.text = _viewModel.user.value?.vehicleList!!.joinToString()

        if (_viewModel.user.value?.hasDrivingLicense == true) {
            driveLic.text = "Yes"
        } else {
            driveLic.text = "No"
        }

        if (_viewModel.user.value?.hasPublicTransportPass == true) {
            publicTrPass.text = "Yes"
            expiryDate.text = _viewModel.user.value?.expiryDate.toString()
        } else {
            publicTrPass.text = "No"
        }

        if (_viewModel.user.value?.canRideBike == true) {
            canRideBike.text = "Yes"
        } else {
            canRideBike.text = "No"
        }

        if (_viewModel.user.value?.canRideScooter == true) {
            canDriveScooter.text = "Yes"
        } else {
            canDriveScooter.text = "No"
        }
    }

}
