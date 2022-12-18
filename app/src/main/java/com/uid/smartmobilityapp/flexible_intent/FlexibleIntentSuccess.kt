package com.uid.smartmobilityapp.flexible_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R

class FlexibleIntentSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_intent_success)

        val backButtonClick = findViewById<Button>(R.id.backButton)
        backButtonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}