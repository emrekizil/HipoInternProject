package com.example.hipointernproject.data.repository

import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.data.source.RemoteDataSource
import com.example.hipointernproject.domain.entity.MemberEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HipoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val memberListMapper: MemberListMapper<Member,MemberEntity>
) :HipoRepository {
    private var members = emptyList<Member>()


    override suspend fun getAllMembers(): Flow<NetworkResponseState<List<MemberEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(val response = remoteDataSource.getAllMembers()){
                is NetworkResponseState.Error -> emit(response)
                NetworkResponseState.Loading -> Unit
                is NetworkResponseState.Success-> {
                    members = response.result!!
                    emit(NetworkResponseState.Success(memberListMapper.map(members)))
                }

            }
        }

    private fun filterMembersByName(name:String){
        members.filter {
            it.name.contains(name)
        }
    }
}