package com.wizeline.heroes.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wizeline.heroes.R
import com.wizeline.heroes.utils.GlideUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_home) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottom_nav_bar).setupWithNavController(navController)
        navController.graph = navController.navInflater.inflate(R.navigation.main_navigation)
    }

    override fun onDestroy() {
        super.onDestroy()
        GlideUtils.eraseInstance()
    }
}
