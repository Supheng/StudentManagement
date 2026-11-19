
package kh.com.supheng.jetpack.compose.studentmanagement.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseDao
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDao
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDatabase
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.UserDao

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStudentDatabase(
        @ApplicationContext context: Context
    ): StudentDatabase {

        return Room.databaseBuilder(
            context,
            StudentDatabase::class.java,
            "student_database"
        )
            .addMigrations(
                StudentDatabase.MIGRATION_1_2,
                StudentDatabase.MIGRATION_2_3
            )
            .build()
    }

    @Provides
    fun provideStudentDao(
        database: StudentDatabase
    ): StudentDao {
        return database.studentDao()
    }

    @Provides
    fun provideCourseDao(
        database: StudentDatabase
    ): CourseDao {
        return database.courseDao()
    }

    @Provides
    fun provideUserDao(
        database: StudentDatabase
    ): UserDao {
        return database.userDao()
    }
}
