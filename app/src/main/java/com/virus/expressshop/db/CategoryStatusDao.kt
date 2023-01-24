package com.virus.expressshop.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.virus.expressshop.data.CategoryStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryStatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(categories:List<CategoryStatus>)
    @Query("select * from category_status")
    fun getCatStatus():Flow<List<CategoryStatus>>


}