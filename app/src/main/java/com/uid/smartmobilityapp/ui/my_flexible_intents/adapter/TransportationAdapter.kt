package com.uid.smartmobilityapp.ui.my_flexible_intents.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.my_flexible_intents.model.Transportation

class TransportationAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Transportation>
) : RecyclerView.Adapter<TransportationViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportationViewHolder {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate(viewType, parent, false)
        // returns the view holder which has been created from the view
        return TransportationViewHolder(view)
    }

    // should return a layout reference that will be sent by the System to the method
    // onCreateViewHolder in the viewType parameter
    override fun getItemViewType(position: Int): Int {
        return R.layout.vehicle_route_view;

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: TransportationViewHolder, position: Int) {
        holder.goButtonRef.visibility= View.GONE
        holder.bindData(dataSource[position])
    }


}