package com.uid.smartmobilityapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.models.VehicleRouteListItem

class VehicleRouteAdapter(
    private val context: Context,
    private val dataSource: ArrayList<VehicleRouteListItem>
) : RecyclerView.Adapter<VehicleRouteAdapter.VehicleRouteViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleRouteViewHolder {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate(viewType, parent, false)
        // returns the view holder which has been created from the view
        return VehicleRouteViewHolder(view)
    }

    // should return a layout reference that will be sent by the System to the method
    // onCreateViewHolder in the viewType parameter
    override fun getItemViewType(position: Int): Int {
        return R.layout.vehicle_route_view;

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: VehicleRouteViewHolder, position: Int) {

        holder.bindData(dataSource.get(position))
    }

    inner class VehicleRouteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var routeImageRef: ImageView
        private lateinit var intervalRef: TextView
        private lateinit var detailsRef: TextView
        private lateinit var goButtonRef: ImageButton
        private lateinit var durationRef: TextView
        init {
            routeImageRef = view.findViewById(R.id.rouetImageViewID)
            intervalRef = view.findViewById(R.id.intervalTextViewID)
            detailsRef = view.findViewById(R.id.detailsTextViewID)
            goButtonRef = view.findViewById(R.id.startRouteButtonID)
            durationRef = view.findViewById(R.id.durationTextViewID)
        }

        fun bindData(data: VehicleRouteListItem) {
            routeImageRef.setImageResource(data.routeImage);
            intervalRef.text = data.interval
            detailsRef.text = data.details
            durationRef.text = data.duration
            goButtonRef.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}