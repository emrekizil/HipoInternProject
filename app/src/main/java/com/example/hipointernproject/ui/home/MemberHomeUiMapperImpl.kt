package com.example.hipointernproject.ui.home

import com.example.hipointernproject.data.mappers.MemberListMapper
import com.example.hipointernproject.domain.entity.MemberEntity
import javax.inject.Inject

class MemberHomeUiMapperImpl @Inject constructor() : MemberListMapper<MemberEntity,HomeUiData>{
    override fun map(input: List<MemberEntity>?): List<HomeUiData> {
        return input?.map {
            HomeUiData(
                age = it.age,
                github = it.github,
                location = it.location,
                name = it.name,
                position = it.position,
                yearsInHipo = it.yearsInHipo
            )
        }?: emptyList()
    }

}