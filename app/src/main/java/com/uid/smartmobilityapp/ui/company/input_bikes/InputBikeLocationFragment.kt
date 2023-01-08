package com.uid.smartmobilityapp.ui.company.input_bikes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.CompanyActivity
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentInputBikeLocationBinding
import com.uid.smartmobilityapp.ui.company.input_bikes.adapters.InputBikeAdapter

class InputBikeLocationFragment : Fragment() {
    private var _binding: FragmentInputBikeLocationBinding? = null
    lateinit private var _viewModel: InputBikeLocationViewModel;
    lateinit private var _root: View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainActivity", "Open Bookmarks Fragment")
        _viewModel =
            ViewModelProvider(this).get(InputBikeLocationViewModel::class.java)

        _binding = FragmentInputBikeLocationBinding.inflate(inflater, container, false)
        _root = binding.root

        setupLocationsRecyclerView()
        setupAddNewLocationButton()

        return _root
    }

    private fun setupAddNewLocationButton() {
        val addNewStopButton: FloatingActionButton = binding.floatingActionButton
        addNewStopButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_input_bike_locations_to_add_input_bike_location)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupLocationsRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.recyclerView2
        val layoutManager =
            LinearLayoutManager(CompanyActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.input_bikes.value?.let {
            InputBikeAdapter(
                CompanyActivity.context,
                it
            )
        }
        bookmarksRecyclerView.layoutManager = layoutManager
        bookmarksRecyclerView.adapter = adapter
        addMessageDividers(bookmarksRecyclerView, layoutManager)
    }

    private fun addMessageDividers(
        recyclerView: RecyclerView,
        linearLayoutManager: LinearLayoutManager
    ) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}