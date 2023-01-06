package com.uid.smartmobilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SigninChooseAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_choose_account)

        setup()
    }

    private fun setup() {
        var companyButton : Button = findViewById(R.id.CompanyButton)
        companyButton.setOnClickListener {
            val intent = Intent ( this, SigninActivity::class.java )
            startActivity(intent)
        }
    }
}