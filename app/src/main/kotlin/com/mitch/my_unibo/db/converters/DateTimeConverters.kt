package com.mitch.my_unibo.db.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeConverters {

    @TypeConverter
    fun convertDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun convertToDate(dateString: String): LocalDate = LocalDate.parse(dateString)

    @TypeConverter
    fun convertTime(time: LocalTime): String = time.toString()

    @TypeConverter
    fun convertToTime(timeString: String): LocalTime = LocalTime.parse(timeString)

    @TypeConverter
    fun convertDateTime(dateTime: LocalDateTime): String = dateTime.toString()

    @TypeConverter
    fun convertToDateTime(dateTimeString: String): LocalDateTime = LocalDateTime.parse(dateTimeString)
}
