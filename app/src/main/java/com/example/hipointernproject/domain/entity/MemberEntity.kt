package com.example.hipointernproject.domain.entity

import com.example.hipointernproject.data.dto.Hipo
import com.google.gson.annotations.SerializedName

data class MemberEntity (
    val age: Int,
    val github: String,
    val location: String,
    val name: String,
    val position: String,
    val yearsInHipo: Int
        )