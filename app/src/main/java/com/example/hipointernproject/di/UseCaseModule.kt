package com.example.hipointernproject.di

import com.example.hipointernproject.domain.usecase.GetAllMembersUseCase
import com.example.hipointernproject.domain.usecase.GetAllMembersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllMembersUseCase(allMembersUseCaseImpl: GetAllMembersUseCaseImpl) : GetAllMembersUseCase
}