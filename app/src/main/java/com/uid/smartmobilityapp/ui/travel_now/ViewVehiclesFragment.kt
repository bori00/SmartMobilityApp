package com.uid.smartmobilityapp.ui.travel_now

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.adapters.VehicleAdapter
import com.uid.smartmobilityapp.databinding.TransportListBinding

class ViewVehiclesFragment: Fragment() {
    private var _binding: TransportListBinding? = null // ce xml ai pt fragment
    lateinit private var _viewModel: ViewVehiclesModel; //Clasa unde pui date pe care le poti shareui intre fragmente
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainActivity", "Open Bookmarks Fragment")
        _viewModel =
            ViewModelProvider(this).get(ViewVehiclesModel::class.java)

        _binding = TransportListBinding.inflate(inflater, container, false)
        _root = binding.root

        setupVehiclesRecyclerView()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupVehiclesRecyclerView() {
        val vehiclesRecyclerView: RecyclerView = binding.transportRecyclerViewList
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.vehicles.value?.let {
            VehicleAdapter(
                MainActivity.context,
                it
            )
        }
        vehiclesRecyclerView.layoutManager = layoutManager
        vehiclesRecyclerView.adapter = adapter
    }

}