package com.uid.smartmobilityapp.ui.my_flexible_intents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentViewFinalRouteBinding
import com.uid.smartmobilityapp.databinding.FragmentViewRouteBinding
import com.uid.smartmobilityapp.ui.travel_now.ViewVehiclesModel

class ViewFinalRouteOptimalFragment: Fragment() {

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
        var i=-1
        if (bundle != null) {
            i = bundle.getInt("image", -1)
        }
        Log.d("TAG",i.toString())
        val img: ImageView = binding.imageView6
        if(i!=-1)
        img.setImageResource(i)

        return _root
    }}