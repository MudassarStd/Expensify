package com.std.composeexpensetracker.data.local

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Locale

class DateStringConverter {
    private val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: Long?): String {
        return value?.let { formatter.format(it) } ?: ""
    }

    @TypeConverter
    fun toTimestamp(dateString: String?): Long {
        return dateString?.let {
            formatter.parse(it)?.time
        } ?: System.currentTimeMillis()
    }
}