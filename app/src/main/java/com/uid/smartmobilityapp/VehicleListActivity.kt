package com.uid.smartmobilityapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.adapters.VehicleAdapter
import com.uid.smartmobilityapp.models.VehicleListItem


class VehicleListActivity: AppCompatActivity()  {

    private val vehicleList: ArrayList<VehicleListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_list)
        vehicleList.add(
            VehicleListItem(
                "Bus",
                "09:56",
                "43B",
                R.drawable.bus,
                false,
                false
            )
        )
        vehicleList.add(
            VehicleListItem(
                "Bike",
                "09:56",
                "43B",
                R.drawable.bike,
                false,
                false
            )
        )
        vehicleList.add(
            VehicleListItem(
                "Walk",
                "09:56",
                "43B",
                R.drawable.walk,
                true,
                false
            )
        )
        vehicleList.add(
            VehicleListItem(
                "Car",
                "09:50",
                "Personal car",
                R.drawable.car,
                false,
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