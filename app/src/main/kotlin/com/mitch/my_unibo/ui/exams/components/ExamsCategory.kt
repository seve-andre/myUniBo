package com.mitch.my_unibo.ui.exams.components

sealed class ExamsCategory(val name: String, val year: Int) {
    object FirstYear : ExamsCategory(
        name = "Primo anno",
        year = 1
    )

    object SecondYear : ExamsCategory(
        name = "Secondo anno",
        year = 2
    )

    object ThirdYear : ExamsCategory(
        name = "Terzo anno",
        year = 3
    )
}
