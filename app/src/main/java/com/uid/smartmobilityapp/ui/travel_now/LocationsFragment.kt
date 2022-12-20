package com.uid.smartmobilityapp.ui.travel_now

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
import com.uid.smartmobilityapp.databinding.FragmentLocationsBinding
import com.uid.smartmobilityapp.ui.travel_now.adapter.LocationAdapter

class LocationsFragment : Fragment() {
    private var _binding: FragmentLocationsBinding? = null
    lateinit private var _viewModel: LocationsViewModel;
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
            ViewModelProvider(this).get(LocationsViewModel::class.java)

        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        _root = binding.root

        setupBookmarksRecyclerView()
//        setupAddNewBookmarkButton()

        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBookmarksRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.locationsRecyclerViewId
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.locations.value?.let {
            LocationAdapter(
                MainActivity.context,
                it
            )
        }
        bookmarksRecyclerView.layoutManager = layoutManager
        bookmarksRecyclerView.adapter = adapter
        addMessageDividers(bookmarksRecyclerView, layoutManager)
    }

//    private fun setupAddNewBookmarkButton() {
//        val addBookmarkFAB: FloatingActionButton = binding.addNewBookmarkFAB
//        addBookmarkFAB.setOnClickListener { view ->
//            view.findNavController().navigate(R.id.action_nav_bookmarks_to_nav_add_bookmark)
//        }
//    }

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