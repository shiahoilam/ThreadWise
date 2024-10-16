package com.example.tryhack

import android.util.Log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.tryhack.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)

        // Set up the navigation controller with the BottomNavigationView
        NavigationUI.setupWithNavController(navView, navController)

        // Handle navigation item selection
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Log.d("Navigation", "Home clicked")
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_carbonfootprint -> {
                    Log.d("Navigation", "Carbon Footprint clicked")
                    navController.navigate(R.id.navigation_carbonfootprint)
                    true
                }
                R.id.navigation_locator -> {
                    Log.d("Navigation", "Locator clicked")
                    navController.navigate(R.id.navigation_locator)
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}