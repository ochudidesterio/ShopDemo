package com.virus.expressshop.network

import com.virus.expressshop.api.ProductsApi
import com.virus.expressshop.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ProductsApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ProductsApi::class.java)
    }
}