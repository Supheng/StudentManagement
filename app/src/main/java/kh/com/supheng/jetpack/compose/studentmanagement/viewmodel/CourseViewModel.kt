package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseEntity
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.CourseRepository
import kh.com.supheng.jetpack.compose.studentmanagement.ui.course.CourseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CourseUiState(isLoading = true)
    )
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    // Keep this property temporarily so your existing CourseScreen
    // can continue observing the course list.
    val courses = uiState
        .map { it.courses }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        observeCourses()
    }

    private fun observeCourses() {
        viewModelScope.launch {
            repository.getAllCourses()
                .catch { exception ->
                    if (exception is CancellationException) {
                        throw exception
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message
                                ?: "Failed to load courses"
                        )
                    }
                }
                .collect { courseList ->
                    _uiState.update {
                        it.copy(
                            courses = courseList,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

//    fun addCourse(course: CourseEntity) {
//        viewModelScope.launch {
//            repository.insertCourse(course)
//        }
//    }
fun addCourse(course: CourseEntity) {
    viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )
        }

        try {
            repository.insertCourse(course)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    successMessage = "Course added successfully"
                )
            }

        } catch (e: CancellationException) {
            throw e

        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to add course"
                )
            }
        }
    }
}

//    fun updateCourse(course: CourseEntity) {
//        viewModelScope.launch {
//            repository.updateCourse(course)
//        }
//    }
fun updateCourse(course: CourseEntity) {
    viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )
        }

        try {
            repository.updateCourse(course)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    successMessage = "Course updated successfully"
                )
            }

        } catch (e: CancellationException) {
            throw e

        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to update course"
                )
            }
        }
    }
}

    fun deleteCourse(course: CourseEntity) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null
                )
            }
            try {
                repository.deleteCourse(course)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "Delete course successfully"
                    )
                }
            }catch (e : CancellationException){
                throw  e
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "fail to delete"
                    )
                }
            }
        }
    }

    fun searchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }
}