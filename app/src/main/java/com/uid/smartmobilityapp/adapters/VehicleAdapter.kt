package com.uid.smartmobilityapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.models.VehicleListItem

class VehicleAdapter(
    private val context: Context,
    private val dataSource: ArrayList<VehicleListItem>
) : RecyclerView.Adapter<VehicleViewHolder>() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int ): VehicleViewHolder
    {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate ( viewType, parent, false )
        // returns the view holder which has been created from the view
        return VehicleViewHolder ( view )
    }

    // should return a layout reference that will be sent by the System to the method
    // onCreateViewHolder in the viewType parameter
    override fun getItemViewType ( position: Int ) : Int
    {
        return R.layout.vehicle_view;

    }
    override fun getItemCount(): Int
    {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bindData ( dataSource.get(position) )
    }
}