package com.example.hipointernproject.ui.home

import android.view.ViewGroup
import com.example.hipointernproject.databinding.AdapterMemberItemBinding
import com.example.hipointernproject.ui.base.BaseViewHolder
import com.example.hipointernproject.utility.inflateAdapterItem

class MemberViewHolder(private val binding : AdapterMemberItemBinding ) : BaseViewHolder<HomeUiData>(binding.root) {
    companion object{
        fun creatForm(parent:ViewGroup) =
            MemberViewHolder(parent.inflateAdapterItem(AdapterMemberItemBinding::inflate))
    }

    override fun onBind(data: HomeUiData) {
        binding.memberComponent.setMemberData(data)
    }

}