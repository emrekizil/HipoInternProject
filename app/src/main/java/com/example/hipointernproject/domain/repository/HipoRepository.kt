package com.example.hipointernproject.domain.repository

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.domain.entity.MemberEntity
import kotlinx.coroutines.flow.Flow

interface HipoRepository {
    suspend fun getAllMembers(): Flow<NetworkResponseState<List<MemberEntity>>>
}