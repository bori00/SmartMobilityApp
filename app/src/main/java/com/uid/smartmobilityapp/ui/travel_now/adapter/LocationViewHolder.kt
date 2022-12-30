package com.uid.smartmobilityapp.ui.travel_now.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class LocationViewHolder ( private val view: View) : RecyclerView.ViewHolder ( view ) {
    private lateinit var nameRef: TextView
    private lateinit var locationNoRef: TextView
    lateinit var deleteButtonRef: FloatingActionButton
    lateinit var editButtonRef: FloatingActionButton

    init
    {
        nameRef = view.findViewById(R.id.LocationNameTFId)
        locationNoRef = view.findViewById(R.id.locationNoId)
        deleteButtonRef = view.findViewById(R.id.deleteLocationButtonId)
        editButtonRef = view.findViewById(R.id.editLocationButtonId)
    }
    fun bindData (data: Location)
    {
        nameRef.text = data.name
        locationNoRef.text = data.indexNo

    }
}