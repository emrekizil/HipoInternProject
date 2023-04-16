package com.example.hipointernproject.domain.usecase

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.domain.entity.MemberEntity
import kotlinx.coroutines.flow.Flow

interface GetAllMembersUseCase {
    suspend operator fun invoke(): Flow<NetworkResponseState<List<MemberEntity>>>
}