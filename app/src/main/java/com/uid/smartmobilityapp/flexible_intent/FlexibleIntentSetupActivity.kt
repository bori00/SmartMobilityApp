package com.uid.smartmobilityapp.flexible_intent

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.uid.smartmobilityapp.R

class FlexibleIntentSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_intent_setup)

        val dropdown = findViewById<Spinner>(R.id.daySpinner)
        val items = arrayOf("Today", "Tomorrow", "In two days")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.adapter = adapter
    }
}