package com.uid.smartmobilityapp.flexible_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.uid.smartmobilityapp.R

class FlexibleIntentSelectTransportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_intent_select_transport)

        val nextButtonClick = findViewById<Button>(R.id.nextButton)
        nextButtonClick.setOnClickListener {
            val intent = Intent(this, FlexibleIntentSelectOptimizationActivity::class.java)
            startActivity(intent)
        }
    }
}