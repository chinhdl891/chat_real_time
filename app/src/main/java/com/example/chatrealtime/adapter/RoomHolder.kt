package com.example.chatrealtime.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatrealtime.BR
import com.example.chatrealtime.databinding.ItemRoomBinding
import com.example.chatrealtime.model.Room

class RoomHolder(var binding : ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindingData(room: Room){
        binding.setVariable(BR.room, room)
        binding.executePendingBindings()
    }
}