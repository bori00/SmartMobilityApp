package com.uid.smartmobilityapp.ui.home.travel_now

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.VehicleListActivity

class TravelNowActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_now)

        val button = findViewById<Button>(R.id.searchRoutesButtonId)
        button.setOnClickListener {
            val intent = Intent(this, VehicleListActivity::class.java)
            startActivity(intent)
        }

        val addressTF = findViewById<TextView>(R.id.addressTextId)

//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

         //Add a marker in Sydney and move the camera
        val bucharest = LatLng(44.4, 26.09)
        mMap.addMarker(MarkerOptions().position(bucharest).title("Marker in bucharest"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bucharest))
    }

}


