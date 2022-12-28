package com.uid.smartmobilityapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.adapters.VehicleAdapter
import com.uid.smartmobilityapp.models.VehicleListItem
import com.uid.smartmobilityapp.models.VehicleRouteListItem


class VehicleListActivity : AppCompatActivity() {

    private val vehicleList: ArrayList<VehicleListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_list)
        vehicleList.add(
            VehicleListItem(
                "Bus",
                "09:56",
                "",
                R.drawable.bus,
                false,
                false,
                false
            )
        )
        vehicleList.add(
            VehicleListItem(
                "Bike",
                "09:56",
                "",
                R.drawable.bike,
                false,
                false,
                false
            )
        )
        vehicleList.add(
            VehicleListItem(
                "Walk",
                "09:56",
                "",
                R.drawable.walk,
                true,
                true,
                false
            )
        )

        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = VehicleAdapter(this, vehicleList)

        val recyclerView = findViewById<RecyclerView>(R.id.transportRecyclerViewList)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }
}