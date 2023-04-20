package com.example.hipointernproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hipointernproject.R
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.di.IoDispatcher
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.usecase.GetAllMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllMembersUseCase: GetAllMembersUseCase,
    private val memberListMapper: MemberListMapper<MemberEntity, HomeUiData>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _memberHomeUiState = MutableLiveData<HomeUiState>()
    val memberHomeUiState: LiveData<HomeUiState> get() = _memberHomeUiState

    var mutableList = mutableListOf<MemberEntity>()
    var filteredList = listOf<MemberEntity>()

    var filteredName = ""

    fun getAllMembers() {
        viewModelScope.launch(ioDispatcher) {
            getAllMembersUseCase().collectLatest { response->
                when (response) {
                    is NetworkResponseState.Success -> {
                        response.result?.let { mutableList.addAll(it) }
                        _memberHomeUiState.postValue(
                            HomeUiState.Success(
                                memberListMapper.map(response.result)
                            )
                        )
                    }
                    is NetworkResponseState.Error -> {
                        _memberHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _memberHomeUiState.postValue(HomeUiState.Loading)
                    }
                }
            }
        }
    }

    fun filterMembers(name: String) {
        filteredName = name
        filteredList = mutableList.filter {
            it.name.lowercase().contains(filteredName.lowercase())
        }
        _memberHomeUiState.postValue(
            HomeUiState.Success(
                memberListMapper.map(filteredList)
            )
        )
    }

    fun checkMemberContains(member:MemberEntity): Boolean =
        mutableList.contains(member)



    fun addMember(
        age: Int,
        github: String,
        position: String,
        yearsInHipo: Int,
        location: String,
        name: String
    ) {
        val newMember = MemberEntity(
            age, github, location, name, position, yearsInHipo
        )
        if(!checkMemberContains(newMember)){
            mutableList.add(newMember)
        }
        _memberHomeUiState.postValue(
            HomeUiState.Success(
                memberListMapper.map(mutableList)
            )
        )
        filterMembers(filteredName)
    }

}