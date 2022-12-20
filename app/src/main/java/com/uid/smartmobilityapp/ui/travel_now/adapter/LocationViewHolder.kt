package com.uid.smartmobilityapp.ui.travel_now.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class LocationViewHolder ( private val view: View) : RecyclerView.ViewHolder ( view ) {
    private lateinit var nameRef: TextView
    private lateinit var locationNoRef: TextView

    init
    {
        nameRef = view.findViewById(R.id.LocationNameTFId)
        locationNoRef = view.findViewById(R.id.locationNoId)
    }
    fun bindData (data: Location)
    {
        nameRef.text = data.name
        locationNoRef.text = data.indexNo

    }
}