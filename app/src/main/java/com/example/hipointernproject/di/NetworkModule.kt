package com.example.hipointernproject.di

import com.example.hipointernproject.data.api.HipoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideHipoApi() : HipoApi{
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/artizco/a957d4e0af6f9d35048808e7200ea076/raw/6c988963e8a9a311e9f6fc0e08e5aa32ddda64ec/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HipoApi::class.java)
    }
}