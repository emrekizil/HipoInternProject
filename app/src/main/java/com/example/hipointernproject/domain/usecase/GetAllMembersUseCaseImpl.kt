package com.example.hipointernproject.domain.usecase

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.repository.HipoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMembersUseCaseImpl @Inject constructor(
    private val repository: HipoRepository
) : GetAllMembersUseCase {
    override suspend fun invoke(): Flow<NetworkResponseState<List<MemberEntity>>> = repository.getAllMembers()
}