package com.example.chatrealtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatrealtime.R
import com.example.chatrealtime.adapter.MessAdapter
import com.example.chatrealtime.base.PPData
import com.example.chatrealtime.data.MyDataLocal
import com.example.chatrealtime.databinding.ActivityChatBinding
import com.example.chatrealtime.model.Mess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityChatBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_chat)
        setContentView(mBinding.root)

        val key: String = intent.extras?.get(getString(R.string.key)) as String
        val userName = intent.extras?.get(getString(R.string.username)) as String
        val database = Firebase.database
        val myRef = database.getReference("Mess/$key")
        val id = intent.extras?.get(getString(R.string.id_user)) as Long
        mBinding.rcvMainMess.layoutManager = LinearLayoutManager(this)
        val adapter = MessAdapter(id, onDelete = {
            PPData.delete("Mess/$key/$it")
        }, onRemove = {
            PPData.deleteRoom(key)
        })

        mBinding.rcvMainMess.adapter = adapter

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listMess = mutableListOf<Mess>()
                for (snapshot in snapshot.children) {
                    val mess: Mess? = snapshot.getValue(Mess::class.java)
                    mess?.key = snapshot.key.toString()
                    mess?.let { listMess.add(it) }
                }
                adapter.initData(listMess)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        mBinding.btnSend.setOnClickListener {
            val content = mBinding.edtSendContent.text.toString()
            if (content.isNotEmpty()) {
                PPData.sendMess(
                    Mess(
                        content = content,
                        username = userName,
                        idUser = id
                    ), key
                )
                mBinding.edtSendContent.text = null
            }
        }
    }
}