package com.example.hipointernproject.data.source

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.dto.Member
interface RemoteDataSource {

    suspend fun getAllMembers():NetworkResponseState<List<Member>>
}