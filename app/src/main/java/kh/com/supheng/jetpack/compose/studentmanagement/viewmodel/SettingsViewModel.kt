package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.SettingsRepository
import kh.com.supheng.jetpack.compose.studentmanagement.ui.state.SettingsUiState

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    val uiState = combine(
        repository.darkMode,
        repository.notifications,
        repository.language
    ) { darkMode, notifications, language ->

        SettingsUiState(
            darkMode = darkMode,
            notifications = notifications,
            language = language
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiState()
    )

    fun updateDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateDarkMode(enabled)
        }
    }

    fun updateNotifications(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateNotifications(enabled)
        }
    }

    fun updateLanguage(language: String) {
        viewModelScope.launch {
            repository.updateLanguage(language)
        }
    }
}
