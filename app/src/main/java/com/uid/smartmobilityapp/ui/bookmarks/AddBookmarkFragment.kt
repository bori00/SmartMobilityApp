package com.uid.smartmobilityapp.ui.bookmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentAddBookmarkBinding
import com.uid.smartmobilityapp.databinding.FragmentBookmarksBinding
import com.uid.smartmobilityapp.ui.bookmarks.adapters.MyBookmarksRecyclerViewAdapter

class AddBookmarkFragment : Fragment() {

    private var _binding: FragmentAddBookmarkBinding ? = null
    lateinit private var _viewModel: AddBookmarkViewModel;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainActivity", "Open Add Bookmark Fragment")
        _viewModel =
            ViewModelProvider(this).get(AddBookmarkViewModel::class.java)

        _binding = FragmentAddBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
}