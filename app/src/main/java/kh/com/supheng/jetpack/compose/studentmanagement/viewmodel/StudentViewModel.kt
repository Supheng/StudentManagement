
package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.StudentRepository
import kh.com.supheng.jetpack.compose.studentmanagement.ui.student.StudentUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState: StateFlow<StudentUiState> = _uiState.asStateFlow()

    init {
        observeStudent()
    }

    private fun observeStudent() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null
                )
            }

            try {
                repository.getAllStudents().collect { students ->
                    _uiState.update {
                        it.copy(
                            students = students,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to load students"
                    )
                }
            }
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null
                )
            }
            try {
                repository.insertStudent(student)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "Student added successfully"
                    )
                }
            }catch (e : CancellationException){
                throw e
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to add student"
                    )
                }
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null
                )
            }
            try {
                repository.deleteStudent(student)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "Delete successfully"
                    )
                }
            }catch (e : CancellationException){
                throw e
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Deleted is fail"
                    )
                }
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null,
                )
            }
            try {
                repository.updateStudent(student)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "student updated successfully"
                    )
                }
            }catch (e : CancellationException){
                throw e
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to Update student"
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
