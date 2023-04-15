package com.example.hipointernproject.data.source

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.api.HipoApi
import com.example.hipointernproject.data.dto.Member
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: HipoApi
) : RemoteDataSource {

    override suspend fun getAllMembers(): NetworkResponseState<List<Member>> =
        try {
            val response = api.getAllMembers()
            NetworkResponseState.Success(response.members)
        }catch (e:Exception){
            NetworkResponseState.Error(e)
        }

}