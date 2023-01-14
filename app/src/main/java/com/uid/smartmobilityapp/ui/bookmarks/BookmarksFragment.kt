package com.uid.smartmobilityapp.ui.bookmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.databinding.FragmentBookmarksBinding
import com.uid.smartmobilityapp.ui.bookmarks.adapters.MyBookmarksRecyclerViewAdapter


class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding ? = null
    lateinit private var _viewModel: BookmarksViewModel;
    lateinit private var _root : View;

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
            ViewModelProvider(this).get(BookmarksViewModel::class.java)

        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        _root = binding.root

        setupBookmarksRecyclerView()
        setupAddNewBookmarkButton()

        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBookmarksRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.myBookmarksRecyclerView
        val layoutManager = LinearLayoutManager (context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.bookmarks.value?.let {
            MyBookmarksRecyclerViewAdapter(requireContext(),
                it
            )
        }
        bookmarksRecyclerView.layoutManager = layoutManager
        bookmarksRecyclerView.adapter = adapter
        addMessageDividers(bookmarksRecyclerView, layoutManager)
    }

    private fun setupAddNewBookmarkButton() {
        val addBookmarkFAB: FloatingActionButton = binding.addNewBookmarkFAB
        addBookmarkFAB.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_nav_bookmarks_to_nav_add_bookmark)
        }
    }

    private fun addMessageDividers(recyclerView: RecyclerView, linearLayoutManager : LinearLayoutManager) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}