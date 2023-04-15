package com.example.hipointernproject.data.dto


import com.google.gson.annotations.SerializedName

data class HipoResponse(
    @SerializedName("company")
    val company: String,
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("team")
    val team: String
)