package com.uid.smartmobilityapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.uid.smartmobilityapp.databinding.ActivityCompanyBinding
import com.uid.smartmobilityapp.ui.company.CompanyViewModel


class CompanyActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCompanyBinding
    private lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        viewModel = CompanyViewModel

        setSupportActionBar(binding.appBarCompany.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_company) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_company_home,
                R.id.nav_company_specifications,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val headerView: View = navView.getHeaderView(0)
        val headerName = headerView.findViewById<View>(R.id.navHeaderMainTitleTextView) as TextView
        headerName.text = viewModel.loggedInUsername.value

        val headerEmail = headerView.findViewById<View>(R.id.navHeaderMainSubtitleTextView) as TextView
        headerEmail.text = viewModel.loggedInEmail.value

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_company)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        lateinit var context: Activity
    }
}