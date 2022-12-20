package com.uid.smartmobilityapp.ui.travel_now.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class LocationViewHolder ( private val view: View) : RecyclerView.ViewHolder ( view ) {
    private lateinit var nameRef: TextView

    init
    {
        nameRef = view.findViewById(R.id.LocationNameTFId)
    }
    fun bindData (data: Location)
    {
        nameRef.text = data.name

    }
}