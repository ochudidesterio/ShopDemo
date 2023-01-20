package com.virus.expressshop.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.virus.expressshop.R
import com.virus.expressshop.databinding.ActivityMainBinding
import com.virus.expressshop.db.ShopDatabase
import com.virus.expressshop.viewmodels.HomeViewModel
import com.virus.expressshop.viewmodels.providers.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel  by lazy {
        val shopDatabase = ShopDatabase.getShopDatabaseInstance(this)
        val homeViewModelFactory = HomeViewModelFactory(shopDatabase)
        ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAllProducts()
        viewModel.getCategories()
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        var navController = Navigation.findNavController(this@MainActivity,R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNav,navController)

    }
}