package com.uid.smartmobilityapp.flexible_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.uid.smartmobilityapp.R

class FlexibleIntentSelectOptimizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_intent_select_optimization)


        val dropdown = findViewById<AutoCompleteTextView>(R.id.selectOptimizationAutoComplete)
        val items = arrayOf("As fast as possible", "Minimal carbon footprint", "Cheapest option")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.setAdapter(adapter)

        val nextButtonClick = findViewById<Button>(R.id.saveFlexibleButton)
        nextButtonClick.setOnClickListener {
            val intent = Intent(this, FlexibleIntentSuccess::class.java)
            startActivity(intent)
        }
    }
}