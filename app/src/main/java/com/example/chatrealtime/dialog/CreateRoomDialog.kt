package com.example.chatrealtime.dialog

import android.content.Context
import com.example.chatrealtime.R
import com.example.chatrealtime.base.BaseDialog
import com.example.chatrealtime.base.PPData
import com.example.chatrealtime.databinding.DialogCreateRoomBinding
import com.example.chatrealtime.model.Room

class CreateRoomDialog(context: Context) : BaseDialog<DialogCreateRoomBinding>(context) {
    override fun setUpdata() {

    }

    override fun setClick() {
        mBinding.btnCreateRoom.setOnClickListener {
            val roomName = mBinding.edtCreateRoomName.text.toString()
            PPData.createRoom(Room(name = roomName, lastMess = "Chào mừng bạn tới $roomName"))
            dismiss()
        }
        mBinding.btnCreateRoomCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun setResizeView() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.dialog_create_room
    }

}