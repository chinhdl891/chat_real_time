package com.example.chatrealtime.model

class Mess(
    var id: Long = System.currentTimeMillis(),
    var content: String = "",
    var username: String = "",
    var idUser: Long = 0L,
    var key: String = ""
)