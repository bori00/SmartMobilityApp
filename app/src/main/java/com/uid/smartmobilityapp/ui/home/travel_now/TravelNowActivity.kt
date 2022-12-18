package com.uid.smartmobilityapp.ui.home.travel_now

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.VehicleListActivity

class TravelNowActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_now)

        val button = findViewById<Button>(R.id.searchRoutesButtonId);
        button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent ( this, VehicleListActivity::class.java )
        startActivity ( intent )

    }
}

