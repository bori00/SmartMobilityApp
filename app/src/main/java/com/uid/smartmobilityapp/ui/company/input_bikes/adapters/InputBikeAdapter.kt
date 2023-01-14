package com.uid.smartmobilityapp.ui.company.input_bikes.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.company.input_bikes.model.InputBike
import com.uid.smartmobilityapp.ui.travel_now.adapter.LocationViewHolder
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class InputBikeAdapter(
    private val context: Context,
    private val dataSource: ArrayList<InputBike>) : RecyclerView.Adapter<InputBikeViewHolder>()
{
    // similar to the ListView adapter we will define here a LayoutInflater that we
    // will use when transforming the XML to Kotlin objects for a specific list element
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputBikeViewHolder {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate ( viewType, parent, false )
        // returns the view holder which has been created from the view
        return InputBikeViewHolder ( view )
    }

    override fun onBindViewHolder(holder: InputBikeViewHolder, position: Int) {
        // binds the view holder with the data
        holder.bindData (dataSource[position])
        holder.deleteButtonRef.setOnClickListener {
            dataSource.removeAt(position)
            it.findNavController().navigate(R.id.action_input_bike_locations_to_input_bike_location)
        }

    }

    override fun getItemViewType ( position: Int ) : Int {
        return R.layout.input_bike_item
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}