package com.uid.smartmobilityapp.input_bikes.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.input_bikes.model.InputBike

class InputBikeViewHolder ( private val view: View) : RecyclerView.ViewHolder ( view )  {
    private lateinit var nameRef: TextView
    private lateinit var bikes_no: TextView
    lateinit var deleteButtonRef: FloatingActionButton

    init
    {
        nameRef = view.findViewById(R.id.nameTVID)
        bikes_no = view.findViewById(R.id.nr_bikesTVID)
        deleteButtonRef = view.findViewById(R.id.deleteButtonID)
    }
    fun bindData (data: InputBike)
    {
        nameRef.text = data.name
        bikes_no.text = data.nr_bikes

    }
}