package com.uid.smartmobilityapp.ui.my_flexible_intents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentOptimalRouteBinding
import com.uid.smartmobilityapp.ui.my_flexible_intents.adapter.TransportationAdapter


class GetOptimalRouteFragment : Fragment() {
    private var _binding: FragmentOptimalRouteBinding? = null
    lateinit private var _viewModel: MyFlexibleIntentsViewModel;
    lateinit private var _root: View;
    var bundle = Bundle()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(MyFlexibleIntentsViewModel::class.java)
        _binding = FragmentOptimalRouteBinding.inflate(inflater, container, false)

        bundle.putInt("image", R.drawable.car_future_intent)

        val sb: SeekBar = binding.seekBar
        val progressTV: TextView = binding.progressSeekBarId

        progressTV.text = "12:20"
        sb.progress = 20

        setupDetails()
        setupRecyclerView()
        setupSeekBar()
        setupSearchRouteButton()

        return binding.root
    }

    private fun setupSearchRouteButton() {
        val searchRouteBtn: Button = binding.showRouteBtnID
        searchRouteBtn.setOnClickListener { view ->
            binding.root.findNavController()
                .navigate(R.id.action_optimal_route_to_view_final_route, bundle)
        }
    }

    private fun setupSeekBar() {
        val sb: SeekBar = binding.seekBar
        val progressTV: TextView = binding.progressSeekBarId

        sb.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val builder_seek = StringBuilder()
                builder_seek
                    .append("13")
                    .append(":")
                    .append("10")
                progressTV.text = builder_seek.toString()

                sb.progress = 70

                val recyclerView: RecyclerView = binding.recyclerView
                val layoutManager =
                    LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
                val adapter = _viewModel.vehicles.value?.let {
                    TransportationAdapter(
                        MainActivity.context,
                        it
                    )
                }
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = adapter

                val vehicleTF = binding.vehicleTFID
                val builder6 = StringBuilder()
                builder6.append("Vehicle: ")
                    .append(_viewModel.vehicles.value?.get(0)?.vehicleName)
                vehicleTF.text = builder6

                val estimatedTimeTF = binding.estimatedTimeTFID
                val builder7 = StringBuilder()
                builder7.append("Estimated time: 0 hours 20 min")
//                    .append(_viewModel.vehicles.value?.get(0)?.estimatedTime)
                estimatedTimeTF.text = builder7

                bundle.putInt("image", R.drawable.bus_future_intent)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                val builder_seek = StringBuilder()
                val h: Int = 12 + progress / 60
                val m: Int = progress % 60
                builder_seek.append(h.toString()).append(":").append(m.toString())
                progressTV.text = builder_seek.toString()


            }
        })

    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        val adapter = _viewModel.car.value?.let {
            TransportationAdapter(
                MainActivity.context,
                it
            )
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun setupDetails() {
        val sb: SeekBar = binding.seekBar
        val progressTV: TextView = binding.progressSeekBarId

        progressTV.text = "12:20"
        sb.progress = 20

        val titleTV = _binding!!.optimalRouteTitleTVId
        val builder = StringBuilder()
        builder.append("Get optimal route for ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.name)
        titleTV.text = builder.toString()

        val destinationTF = binding.destinationTFID
        val builder1 = StringBuilder()
        builder1.append("Destination: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.destination)
        destinationTF.text = builder1

        val vehicleTF = binding.vehicleTFID
        val builder2 = StringBuilder()
        builder2.append("Vehicle: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.vehicleChosen)
        vehicleTF.text = builder2

        val estimatedTimeTF = binding.estimatedTimeTFID
        val builder3 = StringBuilder()
        builder3.append("Estimated time: 0 hours ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.estimatedTime)
            .append(" min")
        estimatedTimeTF.text = builder3

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}