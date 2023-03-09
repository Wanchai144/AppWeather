package com.example.mytestapp.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun formatDt(timestamp: Long): String {
        val timestampAsDateString = DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(timestamp))
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val date = LocalDate.parse(timestampAsDateString, formatter)
        val date_time = "data: $date time: $currentTime"
        return date_time
    }

}