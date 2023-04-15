package com.example.hipointernproject.data.api

import com.example.hipointernproject.data.dto.HipoResponse
import retrofit2.http.GET

interface HipoApi {

    @GET("hipo.json")
    suspend fun getAllMembers():HipoResponse
}