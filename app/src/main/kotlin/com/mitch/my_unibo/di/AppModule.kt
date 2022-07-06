package com.mitch.my_unibo.di

import android.content.Context
import androidx.room.Room
import com.mitch.my_unibo.datastore.UserDataStore
import com.mitch.my_unibo.db.UniboDatabase
import com.mitch.my_unibo.features.course.CourseRepo
import com.mitch.my_unibo.features.course.CourseRepoImpl
import com.mitch.my_unibo.features.email.EmailRepo
import com.mitch.my_unibo.features.email.EmailRepoImpl
import com.mitch.my_unibo.features.exam.ExamRepo
import com.mitch.my_unibo.features.exam.ExamRepoImpl
import com.mitch.my_unibo.features.exam.stats.SimpleStatsCalculator
import com.mitch.my_unibo.features.exam.stats.StatsCalculator
import com.mitch.my_unibo.features.exam.student.StudentExamRepo
import com.mitch.my_unibo.features.exam.student.StudentExamRepoImpl
import com.mitch.my_unibo.features.lesson.LessonRepo
import com.mitch.my_unibo.features.lesson.LessonRepoImpl
import com.mitch.my_unibo.features.notifications.NotificationRepo
import com.mitch.my_unibo.features.notifications.NotificationRepoImpl
import com.mitch.my_unibo.features.student.StudentRepo
import com.mitch.my_unibo.features.student.StudentRepoImpl
import com.mitch.my_unibo.features.tuition.StudentTuitionRepo
import com.mitch.my_unibo.features.tuition.StudentTuitionRepoImpl
import com.mitch.my_unibo.features.virtuale.StudentCourseRepo
import com.mitch.my_unibo.features.virtuale.StudentCourseRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniboDatabase(@ApplicationContext context: Context): UniboDatabase {
        return Room
            .databaseBuilder(
                context,
                UniboDatabase::class.java,
                "Unibo.databases"
            )
            .createFromAsset("databases/my_unibo.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentRepository(db: UniboDatabase): StudentRepo {
        return StudentRepoImpl(db.studentDao)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(db: UniboDatabase): CourseRepo {
        return CourseRepoImpl(db.courseDao)
    }

    @Provides
    @Singleton
    fun provideStudentCoursesRepository(db: UniboDatabase): StudentCourseRepo {
        return StudentCourseRepoImpl(db.studentCourseDao)
    }

    @Provides
    @Singleton
    fun provideExamsRepository(db: UniboDatabase): ExamRepo {
        return ExamRepoImpl(db.examDao)
    }

    @Provides
    @Singleton
    fun provideStudentExamsRepository(db: UniboDatabase): StudentExamRepo {
        return StudentExamRepoImpl(db.studentExamDao)
    }

    @Provides
    @Singleton
    fun provideStudentTuitionRepository(db: UniboDatabase): StudentTuitionRepo {
        return StudentTuitionRepoImpl(db.studentTuitionDao)
    }

    @Provides
    @Singleton
    fun provideEmailRepository(db: UniboDatabase): EmailRepo {
        return EmailRepoImpl(db.emailDao)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(db: UniboDatabase): NotificationRepo {
        return NotificationRepoImpl(db.notificationDao)
    }

    @Provides
    @Singleton
    fun provideLessonRepository(db: UniboDatabase): LessonRepo {
        return LessonRepoImpl(db.lessonDao)
    }

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext context: Context): UserDataStore {
        return UserDataStore(context)
    }

    @Provides
    @Singleton
    fun provideStatsCalculator(
        db: UniboDatabase,
        @ApplicationContext context: Context
    ): StatsCalculator {
        return SimpleStatsCalculator(
            studentRepo = provideStudentRepository(db),
            studentExamRepo = provideStudentExamsRepository(db),
            examRepo = provideExamsRepository(db),
            courseRepo = provideCourseRepository(db),
            userDataStore = provideUserDataStore(context)
        )
    }
}
