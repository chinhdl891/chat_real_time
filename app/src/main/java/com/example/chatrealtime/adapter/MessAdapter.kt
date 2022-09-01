package com.example.chatrealtime.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatrealtime.R
import com.example.chatrealtime.databinding.ItemMessageFriendBinding
import com.example.chatrealtime.databinding.ItemMessageMeBinding
import com.example.chatrealtime.model.Mess

class MessAdapter(var id: Long = 0, val onDelete : (String) -> Unit, val onRemove : () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var oldList = mutableListOf<Mess>()
    val MESS_OF_ME = 1
    val MESS_OF_FRIEND = 2

    fun initData(newList: MutableList<Mess>) {
        val diffUtil = MessDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MESS_OF_ME) {
            val view: ItemMessageMeBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message_me,
                parent,
                false
            )
            return MessMeHolder(view)
        } else {
            val view: ItemMessageFriendBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message_friend,
                parent,
                false
            )
            return MessFriendHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mess = oldList[position]
        if (holder.itemViewType == MESS_OF_ME) {
            val messMeHolder = holder as MessMeHolder
            messMeHolder.bindData(mess)
            messMeHolder.itemView.setOnLongClickListener{
                if (oldList.size == 1){
                    onRemove()
                }
                onDelete(mess.key)
                true
            }
        } else {
            val messFriendHolder = holder as MessFriendHolder
            messFriendHolder.bindingData(mess)
        }
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    override fun getItemViewType(position: Int): Int {
        val mess = oldList[position]
        return if (mess.idUser != id) {
            MESS_OF_FRIEND
        } else {
            MESS_OF_ME
        }
    }
}