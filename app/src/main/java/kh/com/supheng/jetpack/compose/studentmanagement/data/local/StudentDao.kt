package kh.com.supheng.jetpack.compose.studentmanagement.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student : StudentEntity)

    @Query("SELECT * FROM students")
    fun getAllStudents() : Flow<List<StudentEntity>>

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)
}