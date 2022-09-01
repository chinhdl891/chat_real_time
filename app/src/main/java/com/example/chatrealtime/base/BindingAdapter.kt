package com.example.chatrealtime.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("setDateDay")
fun setDateDay(textView: TextView, time: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    val day = calendar.get(Calendar.DATE)
    textView.text = String.format(" %d", day)
    textView.textSize = 15F
}

@BindingAdapter("setDate")
fun setDate(textView: TextView, time: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    val simpleDateFormat = SimpleDateFormat("hh:mm")
    textView.text = simpleDateFormat.format(calendar.timeInMillis)
}



