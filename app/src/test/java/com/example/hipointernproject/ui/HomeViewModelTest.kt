package com.example.hipointernproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.hipointernproject.apiException
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.domain.FakeGetAllMembersUseCase
import com.example.hipointernproject.memberEntityList
import com.example.hipointernproject.memberHomeUiList
import com.example.hipointernproject.ui.home.HomeUiState
import com.example.hipointernproject.ui.home.HomeViewModel
import com.example.hipointernproject.ui.home.MemberHomeUiMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import com.example.hipointernproject.R

class HomeViewModelTest {

    @Mock
    private lateinit var getAllMembersUseCase: FakeGetAllMembersUseCase

    @Mock
    private lateinit var observer:Observer<HomeUiState>

    private val memberHomeUiMapperImpl = MemberHomeUiMapperImpl()

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(getAllMembersUseCase,memberHomeUiMapperImpl)
        viewModel.memberHomeUiState.observeForever(observer)
    }

    @Test
    fun `when use case returned downloading is live data state downloading`(){
        runBlocking {
            Mockito.`when`(getAllMembersUseCase.invoke())
                .thenReturn(
                    flow {
                        emit(NetworkResponseState.Loading)
                    }
                )
            viewModel.getAllMembers()
            verify(observer).onChanged(HomeUiState.Loading)
        }
    }
    @Test
    fun `when use case returned downloading is live data state success`(){
        runBlocking {
            Mockito.`when`(getAllMembersUseCase.invoke())
                .thenReturn(
                    flow {
                        emit(NetworkResponseState.Loading)
                        emit(NetworkResponseState.Success(memberEntityList))
                    }
                )
            viewModel.getAllMembers()
            verify(observer).onChanged(HomeUiState.Loading)
            verify(observer).onChanged(HomeUiState.Success(memberHomeUiList))
        }
    }
    @Test
    fun `when use case returned downloading is live data state error`(){
        runBlocking {
            Mockito.`when`(getAllMembersUseCase.invoke())
                .thenReturn(
                    flow {
                        emit(NetworkResponseState.Loading)
                        emit(NetworkResponseState.Error(apiException))
                    }
                )
            viewModel.getAllMembers()
            verify(observer).onChanged(HomeUiState.Loading)
            verify(observer).onChanged(HomeUiState.Error(R.string.error))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun shutdown(){
        Dispatchers.resetMain()
        viewModel.memberHomeUiState.removeObserver(observer)
    }
}