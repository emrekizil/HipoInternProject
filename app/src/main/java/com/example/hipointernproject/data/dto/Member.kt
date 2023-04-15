package com.example.hipointernproject.data.dto


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("age")
    val age: Int,
    @SerializedName("github")
    val github: String,
    @SerializedName("hipo")
    val hipo: Hipo,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String
)