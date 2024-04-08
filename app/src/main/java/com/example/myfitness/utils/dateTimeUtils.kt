package com.example.myfitness.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    return dateFormat.format(date)
}

fun formatTime(hour:Int,minuts:Int): String {
    val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    return formatNumber(hour)+':'+formatNumber(minuts)
}

fun formatNumber(num:Int):String{
    if(num<10) return "0$num"
    return num.toString()
}