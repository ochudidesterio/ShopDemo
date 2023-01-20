package com.virus.expressshop.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Rating(
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("rate")
    @Expose
    val rate: Double

) {
    override fun toString(): String {
        return "Rating(count=$count, rate=$rate)"
    }
}