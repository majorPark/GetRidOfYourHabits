package com.example.make_routine

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.make_routine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // Add navHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        // 네비게이션 버튼 활성화.
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    // Respond to navigation item 1 click
                    navController.navigate(R.id.actionGlobalHomeFragment)
                    true
                }
                R.id.dashboardFragment -> {
                    // Respond to navigation item 2 click
                    navController.navigate(R.id.actionGlobalDashboardFragment)
                    true
                }
                R.id.settingsFragment -> {
                    // Respond to navigation item 3 click
                    navController.navigate(R.id.actionGlobalSettingsFragment)
                    true
                }
                else -> false
            }
        }
    }


}