package com.example.chatrealtime.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatrealtime.BR
import com.example.chatrealtime.databinding.ItemMessageMeBinding
import com.example.chatrealtime.model.Mess

class MessMeHolder(var itemMessageMeBinding: ItemMessageMeBinding) :
    RecyclerView.ViewHolder(itemMessageMeBinding.root) {
    fun bindData(mess: Mess) {
        itemMessageMeBinding.setVariable(BR.mess, mess)
        itemMessageMeBinding.executePendingBindings()
    }
}