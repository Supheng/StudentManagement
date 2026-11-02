
package kh.com.supheng.jetpack.compose.studentmanagement.data.repository

import kh.com.supheng.jetpack.compose.studentmanagement.data.Model.Student
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDao
import kh.com.supheng.jetpack.compose.studentmanagement.data.mapper.toStudent
import kh.com.supheng.jetpack.compose.studentmanagement.data.mapper.toStudentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    private val studentDao: StudentDao
) {

    fun getAllStudents(): Flow<List<Student>> {
        return studentDao.getAllStudents().map { students ->
            students.map { it.toStudent() }
        }
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student.toStudentEntity())
    }

    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student.toStudentEntity())
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student.toStudentEntity())
    }
}
