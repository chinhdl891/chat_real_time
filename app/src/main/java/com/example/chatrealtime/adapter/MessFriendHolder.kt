package com.example.chatrealtime.adapter

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.chatrealtime.databinding.ItemMessageFriendBinding
import com.example.chatrealtime.model.Mess

class MessFriendHolder(var mbind: ItemMessageFriendBinding) :
    RecyclerView.ViewHolder(mbind.root) {
        fun bindingData(mess: Mess){
            mbind.setVariable(BR.mess,mess)
            mbind.executePendingBindings()
        }
}