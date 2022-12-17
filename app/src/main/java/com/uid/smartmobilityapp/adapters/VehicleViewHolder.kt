package com.uid.smartmobilityapp.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.models.VehicleListItem

class VehicleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var timeStampRef: TextView
    private lateinit var titleRef: TextView
    private lateinit var descriptionRef: TextView
    private lateinit var iconRef: ImageView
    private lateinit var routeRef: TextView
    //   private lateinit var carbonRef?: ImageView
    //   private lateinit var cheapRef?: ImageView

    init {
        timeStampRef = view.findViewById(R.id.hourTextAreaID)
        titleRef = view.findViewById(R.id.titleTextAreaID)
        iconRef = view.findViewById(R.id.vehicleImageId)
        descriptionRef =  view.findViewById(R.id.routeTextAreaID)
    }

    fun bindData(data: VehicleListItem) {
        timeStampRef.text = data.departureTime
        titleRef.text = data.title
        iconRef.setImageResource(data.image)
        descriptionRef.text = data.description
    }
}