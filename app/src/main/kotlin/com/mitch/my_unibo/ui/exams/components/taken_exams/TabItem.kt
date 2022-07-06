package com.mitch.my_unibo.ui.exams.components.taken_exams

sealed class TabItem(val name: String) {
    object AllExams : TabItem(name = "Tutti")
    object TakenExams : TabItem(name = "Sostenuti")
    object ExamsToTake : TabItem(name = "Da sostenere")
}
