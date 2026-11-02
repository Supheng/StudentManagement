package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

import kh.com.supheng.jetpack.compose.studentmanagement.data.Model.Student
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentEntity

data class StudentUiState(
    val students : List<Student> = emptyList(),
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val searchQuery : String = ""
)