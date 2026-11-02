package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseEntity
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.CourseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    val courses = repository.getAllCourses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addCourse(course: CourseEntity) {
        viewModelScope.launch {
            repository.insertCourse(course)
        }
    }

    fun updateCourse(course: CourseEntity) {
        viewModelScope.launch {
            repository.updateCourse(course)
        }
    }

    fun deleteCourse(course: CourseEntity) {
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
    }
}