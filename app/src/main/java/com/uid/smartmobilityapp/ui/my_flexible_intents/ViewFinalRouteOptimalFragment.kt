package com.uid.smartmobilityapp.ui.my_flexible_intents

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
import com.uid.smartmobilityapp.databinding.FragmentViewFinalRouteBinding

class ViewFinalRouteOptimalFragment : Fragment() {

    private var _binding: FragmentViewFinalRouteBinding? = null
    lateinit private var _viewModel: MyFlexibleIntentsViewModel;
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(MyFlexibleIntentsViewModel::class.java)

        _binding = FragmentViewFinalRouteBinding.inflate(inflater, container, false)
        _root = binding.root

        val bundle: Bundle? = arguments
        var i = -1
        if (bundle != null) {
            i = bundle.getInt("image", -1)
        }
        Log.d("TAG", i.toString())
        val img: ImageView = binding.imageView6
        if (i != -1) {
            img.setImageResource(i)
        }

        val okButton: Button = binding.onButtonId
        okButton.setOnClickListener { view ->
            binding.root.findNavController()
                .navigate(R.id.action_view_final_route_to_home)
        }

        return _root
    }
}