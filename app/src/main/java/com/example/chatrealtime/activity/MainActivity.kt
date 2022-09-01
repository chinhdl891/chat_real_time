package com.example.chatrealtime.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatrealtime.R
import com.example.chatrealtime.adapter.RoomAdapter
import com.example.chatrealtime.data.MyDataLocal
import com.example.chatrealtime.databinding.ActivityMainBinding
import com.example.chatrealtime.dialog.CreateAccDialog
import com.example.chatrealtime.dialog.CreateRoomDialog
import com.example.chatrealtime.model.Room
import com.example.chatrealtime.viewmodel.MainActivityViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    var mBinding: ActivityMainBinding? = null
    var roomAdapter: RoomAdapter? = null
    val data = Firebase.database
    val myRef = data.getReference("Room")
    lateinit var dataLocal: MyDataLocal
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mBinding?.root)
        dataLocal = MyDataLocal(this)
        if (dataLocal.getInstall() == 0) {
            val dialog = CreateAccDialog(this, onCreate = {
                dataLocal.setId(System.currentTimeMillis())
                dataLocal.setUseName(it)
                dataLocal.setFirstInstall()
            })
            dialog.apply {
                setCancelable(false)
                show()
            }
        }
        mBinding?.rcvMainListRoom?.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        roomAdapter = RoomAdapter(onGotoMess = {
            val intent = Intent(this, ChatActivity::class.java)
            val useName = dataLocal.getName()
            val id = dataLocal.getId()
            intent.putExtra(getString(R.string.key), it)
            intent.putExtra(getString(R.string.username), useName)
            intent.putExtra(getString(R.string.id_user), id)
            startActivity(intent)
        })
        mBinding?.rcvMainListRoom?.adapter = roomAdapter
        CoroutineScope(Dispatchers.IO).launch {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listRoom = mutableListOf<Room>()
                    for (dataSnapshot in snapshot.children) {
                        val room: Room? = dataSnapshot.getValue(Room::class.java)
                        room?.key = dataSnapshot.key.toString()
                        listRoom.also {
                            room?.let { room1 -> it.add(room1) }
                        }
                    }
                    roomAdapter?.initData(listRoom)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
        mBinding?.btnMainCreateRoom?.setOnClickListener {
            CreateRoomDialog(this).apply {
                show()
                setCancelable(false)
            }
        }
    }

}


