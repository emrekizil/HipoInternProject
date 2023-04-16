package com.example.hipointernproject.data.repository

import com.example.hipointernproject.TestResponseEnum
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.data.mappers.MemberListMapperImpl
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.repository.HipoRepository
import com.example.hipointernproject.memberList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.CacheRequest

class FakeRepository(
    private val memberListMapper: MemberListMapperImpl
) : HipoRepository {

    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }

    override suspend fun getAllMembers(): Flow<NetworkResponseState<List<MemberEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (testRequest) {
                TestResponseEnum.LOADING -> emit(NetworkResponseState.Loading)
                TestResponseEnum.ERROR -> emit(NetworkResponseState.Error(Exception("Error")))
                TestResponseEnum.SUCCESS -> emit(
                    NetworkResponseState.Success(
                        memberListMapper.map(
                            memberList
                        )
                    )
                )
            }
        }
}