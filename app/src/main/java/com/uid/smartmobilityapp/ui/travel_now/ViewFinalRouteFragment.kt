package com.uid.smartmobilityapp.ui.travel_now

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentViewRouteBinding

class ViewFinalRouteFragment : Fragment() {

    private var _binding: FragmentViewRouteBinding? = null
    lateinit private var _viewModel: ViewVehiclesModel;
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(ViewVehiclesModel::class.java)

        _binding = FragmentViewRouteBinding.inflate(inflater, container, false)
        _root = binding.root
        setupHomeButton()
        setupRouteImage()
        return _root
    }

    private fun setupHomeButton() {
        val homeButton: Button = binding.homeButtonID
        homeButton.setOnClickListener { view ->
            binding.root.findNavController().navigate(R.id.action_view_route_to_home)
        }
    }

    private fun setupRouteImage() {
        val routeImage: ImageView = binding.routeImageViewID
        routeImage.setImageResource(R.drawable.route_final)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}