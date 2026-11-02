package kh.com.supheng.jetpack.compose.studentmanagement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "students")
data class StudentEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val email: String,
    val phone: String,
    val course: String
)
