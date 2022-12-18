package com.uid.smartmobilityapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.models.VehicleListItem
import com.uid.smartmobilityapp.models.VehicleRouteListItem

class VehicleAdapter(
    private val context: Context,
    private val dataSource: ArrayList<VehicleListItem>
) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        // interpret the XML file and create Kotlin internal structure
        val view = inflater.inflate(viewType, parent, false)
        // returns the view holder which has been created from the view
        return VehicleViewHolder(view)
    }

    // should return a layout reference that will be sent by the System to the method
    // onCreateViewHolder in the viewType parameter
    override fun getItemViewType(position: Int): Int {
        return R.layout.vehicle_view;

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {

        holder.bindData(dataSource.get(position), position)
    }

    inner class VehicleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val vehicleRouteList: ArrayList<VehicleRouteListItem> = arrayListOf(
            VehicleRouteListItem(
                "Dragalina",
                "Donath",
                "9:43",
                0,
                0,
                "9:56"

            ),
            VehicleRouteListItem(
                "Grigorescu",
                "Belgrad",
                "9:43",
                0,
                0,
                "10:00"
            )
        )
        private var innerRecyclerView: RecyclerView =
            itemView.findViewById(R.id.vehicleRouteRecyclerViewID)
        private var innerAdapter: VehicleRouteAdapter =
            VehicleRouteAdapter(context, vehicleRouteList);

        private lateinit var timeStampRef: TextView
        private lateinit var titleRef: TextView
        private lateinit var descriptionRef: TextView
        private lateinit var iconRef: ImageView
        private lateinit var listRef: RecyclerView
        private lateinit var routeRef: TextView
        //   private lateinit var carbonRef?: ImageView
        //   private lateinit var cheapRef?: ImageView

        init {
            innerRecyclerView.adapter = innerAdapter
            innerRecyclerView.layoutManager = LinearLayoutManager(itemView.getContext())
            timeStampRef = view.findViewById(R.id.hourTextAreaRouteID)
            titleRef = view.findViewById(R.id.location1TextViewID)
            iconRef = view.findViewById(R.id.redDotImageID)
            descriptionRef = view.findViewById(R.id.routeTextAreaID)
            listRef = view.findViewById(R.id.vehicleRouteRecyclerViewID)
        }

        fun bindData(data: VehicleListItem, position: Int) {
            timeStampRef.text = data.departureTime
            titleRef.text = data.title
            iconRef.setImageResource(data.image)
            descriptionRef.text = data.description
            if (data.isExpanded) {
                Log.d("Second recycler", "Visible ")
                listRef.visibility = View.VISIBLE;
            } else {
                Log.d("Second recycler", "Invisible ")
                listRef.visibility = View.GONE;
            }
            this.iconRef.setOnClickListener {
                data.isExpanded = !data.isExpanded
                notifyItemChanged(position)
            }
        }
    }
}