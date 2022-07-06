package com.mitch.my_unibo.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mitch.my_unibo.features.course.Course

class StudentCourseConverters {
    private val gson = Gson()

    @TypeConverter
    fun convertCoursesList(coursesList: List<Course>): String = gson.toJson(coursesList)

    @TypeConverter
    fun convertToCoursesList(coursesJson: String): List<Course> =
        gson.fromJson(coursesJson, Array<Course>::class.java).toList()
}
