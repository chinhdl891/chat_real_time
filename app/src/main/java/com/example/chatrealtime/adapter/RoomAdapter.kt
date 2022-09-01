package com.example.chatrealtime.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatrealtime.R
import com.example.chatrealtime.databinding.ItemRoomBinding
import com.example.chatrealtime.model.Room

private const val TAG = "RoomAdapter"

class RoomAdapter(var onGotoMess : (String) -> Unit) : RecyclerView.Adapter<RoomHolder>() {
    private var listRoom: MutableList<Room> = mutableListOf()
    fun initData(newList: MutableList<Room>) {
        val diffUtil = RoomDiffUtil(listRoom, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listRoom = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val viewBinding: ItemRoomBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_room,
            parent,
            false
        )
        return RoomHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val room = listRoom[position]
        holder.bindingData(room)
        holder.itemView.setOnClickListener {
            onGotoMess(room.key)
        }
    }

    override fun getItemCount(): Int {
        return listRoom.size
    }
}