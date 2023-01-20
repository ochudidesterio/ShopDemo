package com.virus.expressshop.db

import androidx.room.*
import com.virus.expressshop.data.Favourites
import com.virus.expressshop.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourites(favourites: Favourites)
    @Delete
    suspend fun deleteFavourites(favourites: Favourites)
    @Query("select * from favourites")
    fun getAllFavProducts():Flow<List<Favourites>>
}