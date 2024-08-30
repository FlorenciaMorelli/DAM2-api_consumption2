package com.example.api_consumption2.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id")
    val id:Int,

    @SerializedName("title")
    val title:String,

    @SerializedName("image")
    val productImage:String
)
