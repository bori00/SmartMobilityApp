package com.uid.smartmobilityapp.ui.regular_intent

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentRegularIntentSetupBinding
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation
import com.uid.smartmobilityapp.ui.regular_intent.adapter.LocationAdapter
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class RegularIntentSetupFragment : Fragment() {

    private lateinit var viewModel: RegularIntentViewModel

    private var _binding: FragmentRegularIntentSetupBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegularIntentViewModel

        _binding = FragmentRegularIntentSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    private fun setup() {
        setupLocationsRecyclerView()
    }

    private fun setupLocationsRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.locationsRecyclerViewId
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        var locations : ArrayList<RegularIntentLocation> = arrayListOf(
            RegularIntentLocation(null,
                "1",
                null,
                "Specify starting point"
            ),
            RegularIntentLocation(null,
                "1",
                null,
                "Specify destination"
            )
        )
        val adapter = LocationAdapter(
            MainActivity.context,
            locations
        )
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