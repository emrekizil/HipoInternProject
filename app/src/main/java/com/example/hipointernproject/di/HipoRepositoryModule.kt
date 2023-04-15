package com.example.hipointernproject.di

import com.example.hipointernproject.data.repository.HipoRepository
import com.example.hipointernproject.data.repository.HipoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HipoRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHipoRepository(hipoRepositoryImpl: HipoRepositoryImpl):HipoRepository
}