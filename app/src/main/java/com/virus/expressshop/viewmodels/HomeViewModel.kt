package com.virus.expressshop.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virus.expressshop.data.Categories
import com.virus.expressshop.data.Product
import com.virus.expressshop.data.ProductList
import com.virus.expressshop.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var productsListData = MutableLiveData<List<Product>>()
    private var categoriesListData = MutableLiveData<List<String>>()

    fun getAllProducts(){
        RetrofitInstance
            .api
            .getAllProducts()
            .enqueue(object : Callback<ProductList>{
                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    if(response.body() != null){
                        productsListData.value = response.body()
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

}