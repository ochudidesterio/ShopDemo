package com.virus.expressshop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.virus.expressshop.data.Cart
import com.virus.expressshop.data.Favourites
import com.virus.expressshop.data.Product
import com.virus.expressshop.data.coverters.RatingTypeCoverter

@Database(entities = [Product::class,Favourites::class,Cart::class], version = 2)
@TypeConverters(RatingTypeCoverter::class)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao():ProductDao
    abstract fun favoritesDao():FavouritesDao
    abstract fun cartDao():CartDao
    companion object{
        @Volatile
        var INSTANCE: ShopDatabase? = null
        fun getShopDatabaseInstance(context: Context):ShopDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,ShopDatabase::class.java,"shopdatabase")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as ShopDatabase
        }

    }
}