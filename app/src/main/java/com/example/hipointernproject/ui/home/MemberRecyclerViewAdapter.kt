package com.example.hipointernproject.ui.home

import android.view.ViewGroup
import com.example.hipointernproject.ui.base.BaseRecyclerViewAdapter

class MemberRecyclerViewAdapter :BaseRecyclerViewAdapter<HomeUiData,MemberViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder.creatForm(parent)
    }

}