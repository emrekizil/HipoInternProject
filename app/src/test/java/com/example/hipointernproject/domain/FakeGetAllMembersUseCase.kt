package com.example.hipointernproject.domain

import com.example.hipointernproject.apiException
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.usecase.GetAllMembersUseCase
import com.example.hipointernproject.memberEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAllMembersUseCase : GetAllMembersUseCase {
    private var showError = false

    fun updateShowError(showError:Boolean){
        this.showError = showError
    }
    override suspend fun invoke(): Flow<NetworkResponseState<List<MemberEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showError){
                emit(NetworkResponseState.Error(apiException))
            }else{
                emit(NetworkResponseState.Success(memberEntityList))
            }
        }
}