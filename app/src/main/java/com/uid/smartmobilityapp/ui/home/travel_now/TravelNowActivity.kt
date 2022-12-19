package com.uid.smartmobilityapp.ui.home.travel_now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.VehicleListActivity
import java.util.*

class TravelNowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_now)

        val button = findViewById<Button>(R.id.searchRoutesButtonId)
        button.setOnClickListener {
            val intent = Intent(this, VehicleListActivity::class.java)
            startActivity(intent)
        }

        val addressTF = findViewById<TextView>(R.id.addressTextId)

        //initialize places
        Places.initialize(applicationContext, "AIzaSyByB3ZxKmq4KjtdnFDXSoD8axyXmxpEL6I")

        val placesClient: PlacesClient


        //set text view non focusable
        addressTF.isFocusable = false
//        addressTF.setOnClickListener {
//            val fieldList: List<Place.Field> =
//                Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME)
//            val intent: Intent =
//                Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(this)
//
//            startActivityIntent.launch(intent)
//
////            startActivityForResult(intent,100)
//
//
//        }
    }

//    private val startActivityIntent =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                Place place = Autocomplete . getPlaceFromIntent ()
//
//            }
//        }
}

