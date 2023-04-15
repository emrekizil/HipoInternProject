package com.example.hipointernproject.di

import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.data.mappers.MemberListMapperImpl
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.ui.home.HomeUiData
import com.example.hipointernproject.ui.home.MemberHomeUiMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HipoMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMemberRepositoryListMapper(memberListMapperImpl: MemberListMapperImpl)
            : MemberListMapper<Member, MemberEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindMemberHomeUiMapper(memberHomeUiMapperImpl: MemberHomeUiMapperImpl)
            : MemberListMapper<MemberEntity, HomeUiData>
}