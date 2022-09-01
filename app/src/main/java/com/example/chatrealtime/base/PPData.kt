package com.example.chatrealtime.base

import com.example.chatrealtime.model.Mess
import com.example.chatrealtime.model.Room
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object PPData {
    private val dataBase = Firebase.database
    fun createRoom(room1: Room) {
        dataBase.getReference("Room").push().setValue(room1)
    }

    fun sendMess(mess: Mess, keyRoom: String) {
        dataBase.getReference("Mess/$keyRoom").push().setValue(mess)
        dataBase.getReference("Room/$keyRoom").child("lastMess").setValue(mess.content)
        dataBase.getReference("Room/$keyRoom").child("lastTime").setValue(mess.id)
    }

    fun delete(key: String) {
        dataBase.getReference(key).removeValue()
    }

    fun deleteRoom(keyRoom: String){
        dataBase.getReference("Room").child(keyRoom).removeValue()
    }
}

