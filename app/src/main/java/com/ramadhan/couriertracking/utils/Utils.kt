package com.ramadhan.couriertracking.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils{
    fun stringToTime(str: String): Long {
        val localeId = Locale("in", "ID")
        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeId).parse(str)
        return dateTime!!.time
    }
}
