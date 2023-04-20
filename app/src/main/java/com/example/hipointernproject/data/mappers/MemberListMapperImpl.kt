package com.example.hipointernproject.data.mappers

import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.domain.entity.MemberEntity
import javax.inject.Inject

class MemberListMapperImpl @Inject constructor() : MemberListMapper<Member,MemberEntity>{
    override fun map(input: List<Member>?): List<MemberEntity> {
        return input?.map {
            MemberEntity(
                age = it.age,
                github = it.github,
                location = it.location,
                name = it.name,
                position = it.hipo.position.orEmpty(),
                yearsInHipo = it.hipo.yearsInHipo ?: 0
            )
        } ?: emptyList()
    }
}