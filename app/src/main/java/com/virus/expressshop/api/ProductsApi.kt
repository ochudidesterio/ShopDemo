package com.virus.expressshop.api

import com.virus.expressshop.data.Categories
import com.virus.expressshop.data.ProductList
import com.virus.expressshop.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {
    @GET(Constants.PRODUCTS)
     fun getAllProducts():Call<ProductList>
     @GET(Constants.CATEGORIES)
     fun getCategories():Call<List<String>>
}