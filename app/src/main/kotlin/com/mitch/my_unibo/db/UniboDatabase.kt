package com.mitch.my_unibo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mitch.my_unibo.db.converters.CourseConverters
import com.mitch.my_unibo.db.converters.CurrencyConverters
import com.mitch.my_unibo.db.converters.DateTimeConverters
import com.mitch.my_unibo.db.converters.ProfessorConverters
import com.mitch.my_unibo.db.converters.StudentCourseConverters
import com.mitch.my_unibo.features.course.Course
import com.mitch.my_unibo.features.course.CourseDao
import com.mitch.my_unibo.features.email.Email
import com.mitch.my_unibo.features.email.EmailDao
import com.mitch.my_unibo.features.exam.Exam
import com.mitch.my_unibo.features.exam.ExamDao
import com.mitch.my_unibo.features.exam.student.StudentExam
import com.mitch.my_unibo.features.exam.student.StudentExamDao
import com.mitch.my_unibo.features.lesson.Lesson
import com.mitch.my_unibo.features.lesson.LessonDao
import com.mitch.my_unibo.features.notifications.Notification
import com.mitch.my_unibo.features.notifications.NotificationDao
import com.mitch.my_unibo.features.professor.Professor
import com.mitch.my_unibo.features.student.Student
import com.mitch.my_unibo.features.student.StudentDao
import com.mitch.my_unibo.features.tuition.StudentTuition
import com.mitch.my_unibo.features.tuition.StudentTuitionDao
import com.mitch.my_unibo.features.virtuale.StudentCourse
import com.mitch.my_unibo.features.virtuale.StudentCourseDao

@Database(
    entities = [
        Student::class,
        Course::class,
        Lesson::class,
        Professor::class,
        StudentCourse::class,
        Exam::class,
        StudentExam::class,
        StudentTuition::class,
        Notification::class,
        Email::class
    ],
    version = 1
)
@TypeConverters(
    CourseConverters::class,
    ProfessorConverters::class,
    DateTimeConverters::class,
    StudentCourseConverters::class,
    CurrencyConverters::class
)
abstract class UniboDatabase : RoomDatabase() {
    abstract val courseDao: CourseDao
    abstract val studentDao: StudentDao
    abstract val studentCourseDao: StudentCourseDao
    abstract val examDao: ExamDao
    abstract val studentExamDao: StudentExamDao
    abstract val studentTuitionDao: StudentTuitionDao
    abstract val lessonDao: LessonDao
    abstract val notificationDao: NotificationDao
    abstract val emailDao: EmailDao
}
