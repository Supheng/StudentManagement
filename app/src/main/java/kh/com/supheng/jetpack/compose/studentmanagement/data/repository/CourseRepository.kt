
package kh.com.supheng.jetpack.compose.studentmanagement.data.repository

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseDao
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepository @Inject constructor(
    private val courseDao: CourseDao
) {

    fun getAllCourses(): Flow<List<CourseEntity>> {
        return courseDao.getAllCourses()
    }

    suspend fun insertCourse(course: CourseEntity) {
        courseDao.insertCourse(course)
    }

    suspend fun updateCourse(course: CourseEntity) {
        courseDao.updateCourse(course)
    }

    suspend fun deleteCourse(course: CourseEntity) {
        courseDao.deleteCourse(course)
    }
}
