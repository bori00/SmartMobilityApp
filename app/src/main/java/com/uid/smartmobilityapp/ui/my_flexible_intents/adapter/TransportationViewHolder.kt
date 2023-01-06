package com.uid.smartmobilityapp.ui.my_flexible_intents.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.Transportation

class TransportationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var routeImageRef: ImageView
    private lateinit var intervalRef: TextView
    private lateinit var detailsRef: TextView
    public lateinit var goButtonRef: ImageButton
    private lateinit var durationRef: TextView

    init {
        routeImageRef = view.findViewById(R.id.rouetImageViewID)
        intervalRef = view.findViewById(R.id.intervalTextViewID)
        detailsRef = view.findViewById(R.id.detailsTextViewID)
        goButtonRef = view.findViewById(R.id.startRouteButtonID)
        durationRef = view.findViewById(R.id.durationTextViewID)
    }

    fun bindData(data: Transportation) {
        routeImageRef.setImageResource(data.image);
        intervalRef.text = data.intervalTime
        detailsRef.text = data.details
        durationRef.text = data.estimatedTime
    }
}