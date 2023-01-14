package com.uid.smartmobilityapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.uid.smartmobilityapp.databinding.ActivityUserBinding
import com.uid.smartmobilityapp.ui.notifications.NotificationsScheduler


class UserActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserBinding
    private var starterFragmentId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        starterFragmentId = intent.getIntExtra(destinationFragmentIdExtraName, -1)
        Log.d("UserActivity", "Get Extra: " + intent.getIntExtra(destinationFragmentIdExtraName, -1))

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_flexible_intents,
                R.id.nav_regular_intents,
                R.id.nav_report_event,
                R.id.nav_rate_ride,
                R.id.nav_bookmarks
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Log.d("UserActivity", "Navigate to initial fragment: " + starterFragmentId)
        if (starterFragmentId != -1) {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_home, true)
                .build()
            navController.navigate(starterFragmentId, null, navOptions);
        } else {
            NotificationsScheduler().scheduleNotifications()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        lateinit var context: Activity
        val destinationFragmentIdExtraName = "DestinationFragmentId"
    }
}