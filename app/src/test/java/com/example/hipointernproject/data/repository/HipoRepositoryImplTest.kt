package com.example.hipointernproject.data.repository

import android.os.Build.VERSION_CODES.N
import app.cash.turbine.test
import com.example.hipointernproject.TestResponseEnum
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.mappers.MemberListMapperImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HipoRepositoryImplTest {
    @Mock
    private lateinit var memberListMapper:MemberListMapperImpl

    private lateinit var fakeRepository: FakeRepository



    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        fakeRepository = FakeRepository(memberListMapper)
    }

    @Test
    fun `when get all members is first state downloading`(){
        runBlocking {
            fakeRepository.getAllMembers().test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `when get all members response failure is state downloading and failure sequentially`(){
        runBlocking {
            fakeRepository.setRequest(TestResponseEnum.ERROR)
            fakeRepository.getAllMembers().test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
    @Test
    fun `when get all members response success is state downloading and success sequentially`(){
        runBlocking {
            fakeRepository.setRequest(TestResponseEnum.SUCCESS)
            fakeRepository.getAllMembers().test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

}