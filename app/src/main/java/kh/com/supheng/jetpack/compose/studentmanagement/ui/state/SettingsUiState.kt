package kh.com.supheng.jetpack.compose.studentmanagement.ui.state

data class SettingsUiState(
    val darkMode: Boolean = false,
    val notifications: Boolean = true,
    val language: String = "English"
)
