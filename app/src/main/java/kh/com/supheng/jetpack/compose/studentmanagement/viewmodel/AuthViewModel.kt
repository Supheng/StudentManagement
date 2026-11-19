package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import kh.com.supheng.jetpack.compose.studentmanagement.data.model.User
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.AuthRepository

data class AuthUiState(
    val isLoading: Boolean = false,
    val currentUser: User? = null,
    val errorMessage: String? = null,
    val isAuthenticated: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (name.isBlank() || email.isBlank() ||
            password.isBlank() || confirmPassword.isBlank()
        ) {
            showError("Please fill in all fields")
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(email.trim()).matches()
        ) {
            showError("Please enter a valid email")
            return
        }

        if (password.length < 8) {
            showError("Password must contain at least 8 characters")
            return
        }

        if (password != confirmPassword) {
            showError("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = withContext(Dispatchers.IO) {
                authRepository.register(name, email, password)
            }

            result.fold(
                onSuccess = { user ->
                    _uiState.value = AuthUiState(
                        currentUser = user,
                        isAuthenticated = true
                    )
                },
                onFailure = { error ->
                    _uiState.value = AuthUiState(
                        errorMessage = error.message
                            ?: "Registration failed"
                    )
                }
            )
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            showError("Please enter your email and password")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = withContext(Dispatchers.IO) {
                authRepository.login(email, password)
            }

            result.fold(
                onSuccess = { user ->
                    _uiState.value = AuthUiState(
                        currentUser = user,
                        isAuthenticated = true
                    )
                },
                onFailure = { error ->
                    _uiState.value = AuthUiState(
                        errorMessage = error.message
                            ?: "Login failed"
                    )
                }
            )
        }
    }

    fun logout() {
        _uiState.value = AuthUiState()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }

    private fun showError(message: String) {
        _uiState.value = _uiState.value.copy(
            errorMessage = message
        )
    }
}
