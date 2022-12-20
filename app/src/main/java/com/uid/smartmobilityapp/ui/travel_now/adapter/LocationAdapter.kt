package com.uid.smartmobilityapp.ui.travel_now.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.travel_now.model.Location

class LocationAdapter (
    private val context: Context,
    private val dataSource: ArrayList<Location>) : RecyclerView.Adapter<LocationViewHolder>()
    {
        // similar to the ListView adapter we will define here a LayoutInflater that we
        // will use when transforming the XML to Kotlin objects for a specific list element
        private val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
            // interpret the XML file and create Kotlin internal structure
            val view = inflater.inflate ( viewType, parent, false )
            // returns the view holder which has been created from the view
            return LocationViewHolder ( view )
        }

        override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
            // binds the view holder with the data
            holder.bindData (dataSource[position])
        }

        override fun getItemViewType ( position: Int ) : Int {
            return R.layout.location_view
        }

        override fun getItemCount(): Int {
            return dataSource.size
        }
}