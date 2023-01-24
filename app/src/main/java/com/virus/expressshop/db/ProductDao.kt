package com.virus.expressshop.db

import androidx.room.*
import com.virus.expressshop.data.CategoryStatus
import com.virus.expressshop.data.Favourites
import com.virus.expressshop.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)
    @Update
    suspend fun upDateProduct(product: Product)
    @Delete
    suspend fun deleteProduct(product: Product)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(products:List<Product>)
    @Query("Select * from product")
    fun getAllProducts():Flow<List<Product>>
    @Query("select * from product where category =:status")
    fun getActiveStatus(status: String):Flow<List<Product>>
}