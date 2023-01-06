package com.uid.smartmobilityapp.ui.travel_now

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.ui.travel_now.adapter.VehicleAdapter
import com.uid.smartmobilityapp.databinding.TransportListBinding
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations

class ViewVehiclesFragment : Fragment() {
    private var _binding: TransportListBinding? = null
    lateinit private var _viewModel: ViewVehiclesModel
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(ViewVehiclesModel::class.java)

        _binding = TransportListBinding.inflate(inflater, container, false)
        _root = binding.root
        val nextStop: TextView = binding.include.nextStopTextFieldId

        setupLocationSummary()
        setupSummaryButton()
        setupVehiclesRecyclerView()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupLocationSummary() {
        val nextStop: TextView = binding.include.nextStopTextFieldId
        var text: String = "Current location"
        for (loc: Location in MyLocations.locations) {
            if (loc.indexNo.toInt() > 1) {
                val addition = "➔${loc.name}"
                text += addition
            }
        }
        nextStop.text = text
    }

    private fun setupSummaryButton() {
        val summaryButton: Button = binding.include.editRouteButtonId
        summaryButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_view_vehicles_to_locations)
        }
    }

    private fun setupVehiclesRecyclerView() {
        val vehiclesRecyclerView: RecyclerView = binding.transportRecyclerViewList
        val layoutManager =
            LinearLayoutManager(UserActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.vehicles.value?.let {
            VehicleAdapter(
                UserActivity.context,
                it
            )
        }
        vehiclesRecyclerView.layoutManager = layoutManager
        vehiclesRecyclerView.adapter = adapter
    }

}