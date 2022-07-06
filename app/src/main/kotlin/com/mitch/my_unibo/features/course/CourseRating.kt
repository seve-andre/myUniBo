package com.mitch.my_unibo.features.course

class CourseRating(
    rating: Float
) {
    val value = rating

    init {
        require(!(rating < 0 || rating > 10)) { "rating must be between 0 and 10" }
    }
}
