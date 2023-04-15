package com.example.hipointernproject.data.dto


import com.google.gson.annotations.SerializedName

data class Hipo(
    @SerializedName("position")
    val position: String,
    @SerializedName("years_in_hipo")
    val yearsInHipo: Int
)