package com.example.hipointernproject.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.hipointernproject.databinding.LayoutMemberBinding
import com.example.hipointernproject.ui.home.HomeUiData

class MemberUiComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {
    private val binding = LayoutMemberBinding.inflate(LayoutInflater.from(context),this,false)

    init {
        addView(binding.root)
    }

    fun setMemberData(homeUiData: HomeUiData){
        binding.memberName.text = homeUiData.name
    }
}