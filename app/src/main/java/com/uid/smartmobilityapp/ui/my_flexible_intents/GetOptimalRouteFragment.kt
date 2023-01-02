package com.uid.smartmobilityapp.ui.my_flexible_intents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentOptimalRouteBinding
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.FlexibleIntention
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel

class GetOptimalRouteFragment : Fragment() {
    private var _binding: FragmentOptimalRouteBinding? = null
    lateinit private var _viewModel: MyFlexibleIntentsViewModel;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val _viewModel =
            ViewModelProvider(this).get(MyFlexibleIntentsViewModel::class.java)
        _binding = FragmentOptimalRouteBinding.inflate(inflater, container, false)

        //setting up details of the intent
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
        builder3.append("Estimated time: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.estimatedTime)
        estimatedTimeTF.text = builder3

        //setting up details of transportation card
        val image:ImageView = binding.rouetImageViewID
        image.setImageResource(R.drawable.route_vehicles)

        val estimatedCard1TF = binding.durationTextViewID
        val builder4 = StringBuilder()
        builder4.append(_viewModel.flexibleIntents.value?.get(0)?.estimatedTime)
            .append(" min")
        estimatedCard1TF.text = builder4
        
        val intervalCard1TF = binding.intervalTextViewID
        intervalCard1TF.text = "12:00-12:20"

        val detailsTextView = binding.detailsTextViewID
        detailsTextView.text = "Departing at 12:00 from Observator Sud"
        

//        setupTransportationCard()

        val root: View = binding.root
        return root
    }

    private fun setupTransportationCard() {


        val destinationTF = binding.destinationTFID
        val builder = StringBuilder()
        builder.append("Destination: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.destination)
        destinationTF.text = builder

        val vehicleTF = binding.destinationTFID
        builder.append("Vehicle: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.vehicleChosen)
        vehicleTF.text = builder

        val estimatedTimeTF = binding.estimatedTimeTFID
        builder.append("Estimated time: ")
            .append(_viewModel.flexibleIntents.value?.get(0)?.estimatedTime)
        estimatedTimeTF.text = builder

    }
}