package com.uid.smartmobilityapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.models.VehicleRouteListItem

class VehicleRouteAdapter(
    private val context: Context,
    private val dataSource: ArrayList<VehicleRouteListItem>
) : RecyclerView.Adapter<VehicleRouteAdapter.VehicleRouteViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleRouteViewHolder {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate(viewType, parent, false)
        // returns the view holder which has been created from the view
        return VehicleRouteViewHolder(view)
    }

    // should return a layout reference that will be sent by the System to the method
    // onCreateViewHolder in the viewType parameter
    override fun getItemViewType(position: Int): Int {
        return R.layout.vehicle_route_view;

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: VehicleRouteViewHolder, position: Int) {

        holder.bindData(dataSource.get(position))
    }

    inner class VehicleRouteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var redDotImageRef: ImageView
        private lateinit var locationOneRef: TextView
        private lateinit var locationTwoRef: TextView
        private lateinit var middleImageRef: ImageView
        private lateinit var clockImageRef: ImageView
        private lateinit var hourRef: TextView
        private lateinit var departureRef: TextView
        init {
            redDotImageRef = view.findViewById(R.id.redDotImageID)
            locationOneRef = view.findViewById(R.id.location2TextViewID)
            locationTwoRef = view.findViewById(R.id.location1TextViewID)
            middleImageRef = view.findViewById(R.id.arrowImageID)
            clockImageRef = view.findViewById(R.id.clockHourImageID)
            hourRef = view.findViewById(R.id.hourTextAreaRouteID)
            departureRef = view.findViewById(R.id.departureTimeTextViewID)
        }

        fun bindData(data: VehicleRouteListItem) {
            redDotImageRef.setImageResource(R.drawable.reddot);
            locationOneRef.text = data.locationOne
            locationTwoRef.text = data.locationTwo
            middleImageRef.setImageResource(R.drawable.arrow);
            clockImageRef.setImageResource(R.drawable.clock);
            hourRef.text = data.hour
            departureRef.text = data.departureTime
        }
    }
}