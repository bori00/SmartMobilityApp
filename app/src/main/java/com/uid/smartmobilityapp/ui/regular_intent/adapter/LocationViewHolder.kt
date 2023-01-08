package com.uid.smartmobilityapp.ui.regular_intent.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation

class LocationViewHolder ( private val view: View) : RecyclerView.ViewHolder ( view ) {
    var nameRef: TextView
    var locationNoRef: TextView
    var deleteButtonRef: FloatingActionButton
    var editButtonRef: FloatingActionButton

    init
    {
        nameRef = view.findViewById(R.id.LocationNameTFId)
        locationNoRef = view.findViewById(R.id.locationNoId)
        deleteButtonRef = view.findViewById(R.id.deleteLocationButtonId)
        editButtonRef = view.findViewById(R.id.editLocationButtonId)
    }
    fun bindData (data: RegularIntentLocation)
    {
        nameRef.text = data.name
        locationNoRef.text = data.indexNo
    }
}