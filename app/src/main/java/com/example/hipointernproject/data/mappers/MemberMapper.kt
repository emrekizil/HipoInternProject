package com.example.hipointernproject.data.mappers

interface MemberMapper <I,O> {
    fun map(input:I?):O
}