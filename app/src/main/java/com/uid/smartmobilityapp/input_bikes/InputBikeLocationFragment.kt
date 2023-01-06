package com.uid.smartmobilityapp.input_bikes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentInputBikeLocationBinding
import com.uid.smartmobilityapp.input_bikes.adapters.InputBikeAdapter

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
//        setupSearchRoutesButton()
//        setupAddNewStopButton()
//

        return _root
    }
//
//    private fun setupAddNewStopButton() {
//        val addNewStopButton: Button = binding.addNewStopButtonId
//        addNewStopButton.setOnClickListener { view ->
//            view.findNavController().navigate(R.id.action_locations_to_travel_now)
//        }
//    }
//
//    private fun setupSearchRoutesButton() {
//        val searchRoutesButton: Button = binding.searchRoutesButton2Id
//        searchRoutesButton.setOnClickListener { view ->
//            binding.root.findNavController().navigate(R.id.action_locations_to_vehicle_list)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupLocationsRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.recyclerView2
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.input_bikes.value?.let {
            InputBikeAdapter(
                MainActivity.context,
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