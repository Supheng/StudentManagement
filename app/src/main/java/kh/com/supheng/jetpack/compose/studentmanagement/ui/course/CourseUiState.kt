package kh.com.supheng.jetpack.compose.studentmanagement.ui.course

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseEntity

data class CourseUiState(
    val courses: List<CourseEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)