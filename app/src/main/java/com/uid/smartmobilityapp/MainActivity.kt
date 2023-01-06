package com.uid.smartmobilityapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.uid.smartmobilityapp.databinding.ActivityMainBinding
import com.uid.smartmobilityapp.ui.notifications.NotificationsScheduler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        var loginButton : Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent ( this, LoginActivity::class.java )
            startActivity(intent)
        }

        var signupButton : Button = findViewById(R.id.signupButton)
        signupButton.setOnClickListener {
            val intent = Intent ( this, SigninChooseAccountActivity::class.java )
            startActivity(intent)
        }
    }
}