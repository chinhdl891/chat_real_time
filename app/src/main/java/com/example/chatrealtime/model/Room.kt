package com.example.chatrealtime.model

class Room (
    var id: Long = System.currentTimeMillis(),
    var lastMess: String = "",
    var lastTime: Long = System.currentTimeMillis(),
    var name: String = "",
    var key: String = ""
    )
