package com.example.hipointernproject

import com.example.hipointernproject.data.dto.Hipo
import com.example.hipointernproject.data.dto.Member
import com.example.hipointernproject.domain.entity.MemberEntity
import com.example.hipointernproject.ui.home.HomeUiData
import com.google.common.annotations.VisibleForTesting

@VisibleForTesting
const val SAMPLE_MEMBER_RESPONSE_FILE_NAME ="MemberResponse.json"


@VisibleForTesting
val memberEntity = MemberEntity(
    33,
    "artizco",
    "Istanbul",
    "Yasin Cetiner",
    "Android Team Lead",
    5
)

val hipoResult = Hipo(
    "Android Team Lead",
    5
)
@VisibleForTesting
val memberResult = Member(
    33,
    "artizco",
    hipoResult,
    "Istanbul",
    "Yasin Cetiner"
)

val memberHomeUiData = HomeUiData(
    33,
    "artizco",
    "Istanbul",
    "Yasin Cetiner",
    "Android Team Lead",
    5
)

enum class TestResponseEnum{
    ERROR,
    LOADING,
    SUCCESS
}

@VisibleForTesting
val memberEntityList = mutableListOf(memberEntity)

@VisibleForTesting
val memberList = mutableListOf(memberResult)

@VisibleForTesting
val memberHomeUiList = mutableListOf(memberHomeUiData)
@VisibleForTesting
val apiException = Exception("Something went wrong")

