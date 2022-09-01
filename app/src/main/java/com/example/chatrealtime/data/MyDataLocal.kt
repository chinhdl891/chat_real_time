package com.example.chatrealtime.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.example.chatrealtime.R

class MyDataLocal(var context: Activity) {
    private val sharedPref = context.getPreferences(Context.MODE_PRIVATE)

    fun setId(id: Long) {
        with(sharedPref.edit()) {
            putLong(context.getString(R.string.id), id)
            apply()
        }
    }

    fun getId(): Long {
        val defaultValue = 0L
        return sharedPref.getLong(context.getString(R.string.id), defaultValue)
    }

    fun setFirstInstall() {
        with(sharedPref.edit()) {
            putInt(context.getString(R.string.the_first), 1)
            apply()
        }
    }

    fun getInstall(): Int {
        return sharedPref.getInt(context.getString(R.string.the_first), 0)
    }

    fun setUseName(useName: String) {
        with(sharedPref.edit()) {
            putString(context.getString(R.string.txt_use_name), useName)
            apply()
        }
    }

    fun getName(): String {
        return sharedPref.getString(
            context.getString(R.string.txt_use_name),
            "User ${System.currentTimeMillis()}"
        )
            .toString()
    }
}