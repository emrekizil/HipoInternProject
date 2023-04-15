package com.example.hipointernproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hipointernproject.R
import com.example.hipointernproject.data.NetworkResponseState
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.data.repository.HipoRepository
import com.example.hipointernproject.domain.entity.MemberEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HipoRepository,
    private val memberListMapper:MemberListMapper<MemberEntity,HomeUiData>
) : ViewModel() {
    private val _memberHomeUiState = MutableLiveData<HomeUiState>()
    val memberHomeUiState : LiveData<HomeUiState> get() = _memberHomeUiState

    fun getAllMembers(){
        viewModelScope.launch {
            repository.getAllMembers().collectLatest {
                when(it){
                    is NetworkResponseState.Success->{
                        _memberHomeUiState.postValue(HomeUiState.Success(memberListMapper.map(it.result)))
                    }
                    is NetworkResponseState.Error->{
                        _memberHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    is NetworkResponseState.Loading->{
                        _memberHomeUiState.postValue(HomeUiState.Loading)
                    }
                }
            }
        }
    }
}