package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student

data class StudentUiState(
    val students : List<Student> = emptyList(),
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val searchQuery : String = "",
    val successMessage : String? = null
)