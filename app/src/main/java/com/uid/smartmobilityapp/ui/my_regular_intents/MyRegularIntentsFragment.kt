package com.uid.smartmobilityapp.ui.my_regular_intents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.databinding.FragmentMyRegularIntentsBinding

class MyRegularIntentsFragment : Fragment() {

    private var _binding: FragmentMyRegularIntentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(MyRegularIntentsViewModel::class.java)

        _binding = FragmentMyRegularIntentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.myRegularIntentsTitleTextView
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}