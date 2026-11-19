package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.SettingsRepository

@HiltViewModel
class ThemeViewModel @Inject constructor(
    repository: SettingsRepository
) : ViewModel() {

    val darkMode = repository.darkMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )
}