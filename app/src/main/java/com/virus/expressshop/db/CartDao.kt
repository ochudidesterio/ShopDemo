package com.virus.expressshop.db

import androidx.room.*
import com.virus.expressshop.data.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Query("select * from cart")
    fun getCartItems():Flow<List<Cart>>

}