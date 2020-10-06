package com.ramadhan.couriertracking.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils{
    fun stringToTime(str: String): Long {
        val localeId = Locale("in", "ID")
        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeId).parse(str)
        return dateTime!!.time
    }
}
