package com.virus.expressshop.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.virus.expressshop.R
import com.virus.expressshop.databinding.ActivityMainBinding
import com.virus.expressshop.db.ShopDatabase
import com.virus.expressshop.viewmodels.HomeViewModel
import com.virus.expressshop.viewmodels.providers.HomeViewModelFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    companion object{
        val user = Firebase.auth.currentUser
    }
    val viewModel  by lazy {
        val shopDatabase = ShopDatabase.getShopDatabaseInstance(this)
        val homeViewModelFactory = HomeViewModelFactory(shopDatabase)
        ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            Log.e( "onCreate: ", user?.email!!)
        }catch (e: Exception){
            e.printStackTrace()
        }
        viewModel.getAllProducts()
        viewModel.getCategories()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val bottomNav = binding.bottomNav
        val navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(bottomNav,navController)
        // always show selected Bottom Navigation item as selected (return true)
        bottomNav.setOnItemSelectedListener { item ->
            // In order to get the expected behavior, you have to call default Navigation method manually
            NavigationUI.onNavDestinationSelected(item, navController)

            return@setOnItemSelectedListener true
        }


    }
}