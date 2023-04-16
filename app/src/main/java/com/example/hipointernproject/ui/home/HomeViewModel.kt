package com.example.hipointernproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hipointernproject.R
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.domain.repository.HipoRepository
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.domain.usecase.GetAllMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllMembersUseCase: GetAllMembersUseCase,
    private val memberListMapper: MemberListMapper<MemberEntity, HomeUiData>
) : ViewModel() {
    private val _memberHomeUiState = MutableLiveData<HomeUiState>()
    val memberHomeUiState: LiveData<HomeUiState> get() = _memberHomeUiState

    var mutableList = mutableListOf<MemberEntity>()
    var filteredList = listOf<MemberEntity>()

    var isFiltering = false
    fun getAllMembers() {
        viewModelScope.launch {
            getAllMembersUseCase.invoke().collectLatest { response->
                when (response) {
                    is NetworkResponseState.Success -> {
                        //mutableList = it.result?.toMutableList()!!
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
        isFiltering = true
        filteredList = mutableList.filter {
            it.name.lowercase().contains(name.lowercase())
        }
        _memberHomeUiState.postValue(
            HomeUiState.Success(
                memberListMapper.map(filteredList)
            )
        )
    }

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
        mutableList.add(newMember)
        _memberHomeUiState.postValue(
            HomeUiState.Success(
                memberListMapper.map(mutableList)
            )
        )
       /* if (isFiltering){
            filteredList = filteredList + newMember
            _memberHomeUiState.postValue(
                HomeUiState.Success(
                    memberListMapper.map(filteredList)
                )
            )
        }else{
            mutableList.add(newMember)
            _memberHomeUiState.postValue(
                HomeUiState.Success(
                    memberListMapper.map(mutableList)
                )
            )
        }*/

    }

}