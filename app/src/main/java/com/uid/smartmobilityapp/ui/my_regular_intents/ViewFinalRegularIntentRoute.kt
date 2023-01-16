package com.uid.smartmobilityapp.ui.my_regular_intents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFinalRegularIntentRouteBinding
import com.uid.smartmobilityapp.models.VehicleRouteListItem
import com.uid.smartmobilityapp.ui.travel_now.adapter.VehicleRouteAdapter

class ViewFinalRegularIntentRoute : Fragment() {
    private var _binding: FragmentFinalRegularIntentRouteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(MyRegularIntentsViewModel::class.java)

        _binding = FragmentFinalRegularIntentRouteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupImage()
        setOkButton()
        setFooterCard()
        return root
    }

    private fun setFooterCard() {

        var innerDetailsTextView: TextView = binding.include.detailsTextViewID
        innerDetailsTextView.text = "Scheduled at 17:05 from SINTEROM NORD";
        var innerIntervalTextView: TextView = binding.include.intervalTextViewID
        innerIntervalTextView.text = "17:05-17:28";
        var innerDurationTextView: TextView = binding.include.durationTextViewID
        innerDurationTextView.text = "23 min";
        var innerImageView: ImageView = binding.include.rouetImageViewID
        innerImageView.setImageResource(R.drawable.route)
        var button :ImageButton = binding.include.startRouteButtonID
        button.visibility = View.INVISIBLE

    }

    private fun setOkButton() {
        val okButton: Button = binding.okButtonID
        okButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.go_home)
        }
    }

    private fun setupImage() {
        val routeImage: ImageView = binding.routeRegularFinalmageViewID
        routeImage.setImageResource(R.drawable.route_final)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}