package com.example.chatrealtime.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.chatrealtime.model.Room
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val listRoom: MutableLiveData<MutableList<Room>> = MutableLiveData(mutableListOf())
    val data = Firebase.database
    val myRef = data.getReference("Room")
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            myRef.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val room: Room? = snapshot.getValue(Room::class.java)
                    room?.key = snapshot.key.toString()
                    listRoom.value = listRoom.value.also {
                        room?.let { room -> it?.add(room) }
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }

    fun loadData2() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val room: Room? = dataSnapshot.getValue(Room::class.java)
                    room?.key = dataSnapshot.key.toString()
                    listRoom.value = listRoom.value.also {
                        room?.let { room1 -> it?.add(room1) }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}