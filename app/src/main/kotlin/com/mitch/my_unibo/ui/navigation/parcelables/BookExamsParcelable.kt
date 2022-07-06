package com.mitch.my_unibo.ui.navigation.parcelables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookExamsParcelable(
    val examsToBook: List<Int>
) : Parcelable
