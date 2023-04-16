package com.example.hipointernproject.data.repository

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.data.source.RemoteDataSource
import com.example.hipointernproject.di.IoDispatcher
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.repository.HipoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HipoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val memberListMapper: MemberListMapper<Member, MemberEntity>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HipoRepository {

    private var memberList = emptyList<Member>()
    var isFiltering = false
    var firstTime = true
    override suspend fun getAllMembers(): Flow<NetworkResponseState<List<MemberEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getAllMembers()) {
                is NetworkResponseState.Error -> emit(response)
                NetworkResponseState.Loading -> Unit
                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(memberListMapper.map(response.result)))
                }
            }
        }.flowOn(ioDispatcher)

}

