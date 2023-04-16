package com.example.hipointernproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class CoroutineDispatchersModule {
    @IoDispatcher
    @Provides
    @ViewModelScoped
    fun provideIoDispatchers() = Dispatchers.IO
}