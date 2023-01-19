package com.virus.expressshop.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Product(
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("price")
    @Expose
    val price: Double,
    @SerializedName("rating")
    @Expose
    val rating: Rating,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("isFavorite")
    @Expose
    var isFavorite: Boolean


) {
    override fun toString(): String {
        return "Product(category='$category', description='$description', id=$id, image='$image', price=$price, rating=$rating, title='$title', isFavorite=$isFavorite)"
    }
}