package com.virus.expressshop.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virus.expressshop.db.ShopDatabase
import com.virus.expressshop.viewmodels.HomeViewModel

class HomeViewModelFactory(private val shopDatabase: ShopDatabase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(shopDatabase) as T
    }
}