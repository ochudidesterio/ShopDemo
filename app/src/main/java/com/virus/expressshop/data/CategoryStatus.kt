package com.virus.expressshop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_status")
data class CategoryStatus (
    @PrimaryKey
    var category:String)