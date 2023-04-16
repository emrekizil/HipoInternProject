package com.example.hipointernproject.data.source

import com.example.hipointernproject.apiException
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.api.HipoApi
import com.example.hipointernproject.data.dto.HipoResponse
import com.example.hipointernproject.memberList
import com.example.hipointernproject.memberResult
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {
    @Mock
    private lateinit var hipoApi: HipoApi

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(hipoApi)
    }

    @Test
    fun `when member list returned is state success`(){
        runBlocking {
            Mockito.`when`(hipoApi.getAllMembers())
                .thenReturn(
                    HipoResponse("Hipo", memberList,"Android")
                )
            val response = remoteDataSourceImpl.getAllMembers()
            assertThat(response).isInstanceOf(NetworkResponseState.Success::class.java)
        }
    }

    @Test
    fun `when hipo api member returned null is state failure`(){
        runBlocking {
            Mockito.`when`(hipoApi.getAllMembers())
                .thenReturn(
                    null
                )
            val response = remoteDataSourceImpl.getAllMembers()
            assertThat(response).isInstanceOf(NetworkResponseState.Error::class.java)
        }
    }


}