package com.virus.expressshop.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virus.expressshop.data.*
import com.virus.expressshop.db.ShopDatabase
import com.virus.expressshop.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val shopDatabase: ShopDatabase):ViewModel() {
    private var productsListData = MutableLiveData<List<Product>>()
    private var categoriesListData = MutableLiveData<List<String>>()
    private var categories = mutableListOf("All")
    private  var statuList = mutableListOf<CategoryStatus>()
    var product = MutableLiveData<Product>()

    fun getActiveStatus(status: String):Flow<List<Product>> = shopDatabase.productDao().getActiveStatus(status)

    fun insertCategoryStatus(list:List<CategoryStatus>){
        viewModelScope.launch {
            shopDatabase.categoryStatusDao().insertAll(list)
        }
    }

    fun getCatStatus():Flow<List<CategoryStatus>> = shopDatabase.categoryStatusDao().getCatStatus()

    fun addToCart(cart: Cart){
        viewModelScope.launch {
            shopDatabase.cartDao().insertCart(cart)
        }
    }
    fun removeFromCart(cart: Cart){
        viewModelScope.launch {
            shopDatabase.cartDao().deleteCart(cart)
        }
    }
    fun getCartItems():Flow<List<Cart>> = shopDatabase.cartDao().getCartItems()

    fun insertFavourites(favourites: Favourites){
        viewModelScope.launch {
            shopDatabase.favoritesDao().insertFavourites(favourites)
        }
    }
    fun deleteFavorites(favourites: Favourites){
        viewModelScope.launch {
            shopDatabase.favoritesDao().deleteFavourites(favourites)
        }
    }
    fun upDateProduct(product: Product){
        viewModelScope.launch {
            shopDatabase.productDao().upDateProduct(product)
        }
    }
    fun insertAll(product: List<Product>){
        viewModelScope.launch {
            shopDatabase.productDao().insertAll(product)
        }
    }
    fun getAllCachedProduct():Flow<List<Product>> = shopDatabase.productDao().getAllProducts()
    fun getAllFavourites():Flow<List<Favourites>> = shopDatabase.favoritesDao().getAllFavProducts()

    fun getAllProducts(){
        RetrofitInstance
            .api
            .getAllProducts()
            .enqueue(object : Callback<ProductList>{
                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    if(response.body() != null){
                        productsListData.value = response.body()
                        insertAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e( "onFailure: ",t.message.toString() )
                }

            })
    }
    fun getCategories(){
        RetrofitInstance
            .api
            .getCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                    if(response.body() != null){
                        categoriesListData.value = response.body()
                        categories.addAll(response.body()!!)
                        for (i in categories.indices){
                           val categoryStatus = CategoryStatus( categories[i])
                            statuList.add(categoryStatus)
                        }
                        insertCategoryStatus(statuList)
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("onFailure: ",t.message.toString() )
                }

            })
    }
    fun AllProductsObserver():LiveData<List<Product>>{
        return productsListData
    }
    fun CategoriesObserver():LiveData<List<String>>{
        return categoriesListData
    }
    fun getProduct():LiveData<Product>{
        return product
    }

}