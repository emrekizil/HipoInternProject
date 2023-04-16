package com.example.hipointernproject.ui

import com.example.hipointernproject.memberEntityList
import com.example.hipointernproject.ui.home.HomeUiData
import com.example.hipointernproject.ui.home.MemberHomeUiMapperImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MemberHomeUiMapperImplTest {
    private val memberHomeUiMapperImpl = MemberHomeUiMapperImpl()
    private lateinit var uiElements : List<HomeUiData>


    @Before
    fun setup(){
        uiElements = memberHomeUiMapperImpl.map(memberEntityList)
    }

    @Test
    fun `when entity mapped is name correct`(){
        assertThat(uiElements[0].name).isEqualTo(memberEntityList[0].name)
    }
    @Test
    fun `when entity mapped is age correct`(){
        assertThat(uiElements[0].age).isEqualTo(memberEntityList[0].age)
    }
    @Test
    fun `when entity mapped is github correct`(){
        assertThat(uiElements[0].github).isEqualTo(memberEntityList[0].github)
    }
    @Test
    fun `when entity mapped is location correct`(){
        assertThat(uiElements[0].location).isEqualTo(memberEntityList[0].location)
    }
    @Test
    fun `when entity mapped is position correct`(){
        assertThat(uiElements[0].position).isEqualTo(memberEntityList[0].position)
    }
    @Test
    fun `when entity mapped is yearsInHipo correct`(){
        assertThat(uiElements[0].yearsInHipo).isEqualTo(memberEntityList[0].yearsInHipo)
    }
    @Test
    fun `when input entity is null is result empty`(){
        val newUiElements = memberHomeUiMapperImpl.map(null)
        assert(newUiElements.isEmpty())
    }

}