package com.virus.expressshop.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.virus.expressshop.R
import com.virus.expressshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        var navController = Navigation.findNavController(this@MainActivity,R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNav,navController)

    }
}